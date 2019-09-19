package com.example.batchforscience.config;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.ClientEntity;
import com.example.batchforscience.mappers.ClientMapper;
import com.example.batchforscience.processor.ClientItemProcessor;
import com.example.batchforscience.repository.ClientRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private @Value("${batch.folders.clients}") String clientsFolder;
	private @Value("${batch.chunk.size}") int chunkSize;
	private @Value("${batch.linestoskip}") int linesToSkip;
	private @Value("${batch.delimiter}") String delimiter;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JdbcBatchItemWriter<ClientEntity> writerJdbc;
	
	@Autowired
	private RepositoryItemWriter<ClientEntity> writerRepo;

	@Autowired
	private FlatFileItemReader<Client> personItemReader;

	@Bean("partitioner")
	@StepScope
	public Partitioner partitioner() {
		MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources(clientsFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		partitioner.setResources(resources);
		partitioner.partition(10);
		return partitioner;
	}

	@Bean
	public ClientItemProcessor processor() {
		return new ClientItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<ClientEntity> writerJdbc(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ClientEntity>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO client (id, full_name, address, telephone, identity_number, description) "
                		+ "VALUES (:id, :fullName, :address, :telephone, :identityNumber, :description)")
                .dataSource(dataSource)
                .build();
	}
	
	@Bean
	public RepositoryItemWriter<ClientEntity> writerRepo(ClientRepository clientRepository) {
		return new RepositoryItemWriterBuilder<ClientEntity>()
				.repository(clientRepository)
				.methodName("save")
				.build();
	}

	@Bean
	public Job importUserJob(Step toEntity) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.flow(masterStep())
				.end()
				.build();
	}

	@Bean
	public Step toEntity() {
		return stepBuilderFactory.get("toEntity")
				.<Client, ClientEntity>chunk(chunkSize)
				.processor(processor())
				.writer(writerRepo)
				.reader(personItemReader)
				.build();
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

	@Bean
	@Qualifier("masterStep")
	public Step masterStep() {
		return stepBuilderFactory.get("masterStep")
				.partitioner("toEntity", partitioner())
				.step(toEntity())
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	@StepScope
	@Qualifier("clientItemReader")
	@DependsOn("partitioner")
	public FlatFileItemReader<Client> clientItemReader(@Value("#{stepExecutionContext['fileName']}") String filename)
			throws MalformedURLException {
		
		DefaultLineMapper<Client> lineMapper = new DefaultLineMapper<Client>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer(delimiter));
		lineMapper.setFieldSetMapper(new ClientMapper());

		return new FlatFileItemReaderBuilder<Client>().name("clientItemReader")
				.resource(new UrlResource(filename))
				.delimited()
				.names(new String[]{"id", "firstName", "lastName", "description", "address", "telephone", "identityNumber"})
				.lineMapper(lineMapper)
				.linesToSkip(linesToSkip)
				.build();
	}      
	
}
