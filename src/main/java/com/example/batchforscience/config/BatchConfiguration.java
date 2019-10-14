package com.example.batchforscience.config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
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
import com.example.batchforscience.listener.JobCompletionListener;
import com.example.batchforscience.mappers.ClientMapper;
import com.example.batchforscience.processor.ClientItemProcessor;
import com.example.batchforscience.reader.MultilineReader;
import com.example.batchforscience.writer.ClientItemWriter;

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
	private MultilineReader customReader;
	
	@Autowired
	private ClientItemWriter customWriter;
	
	@Autowired
	private ClientItemProcessor clientItemProcessor;
	
	
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
	public Job importClientJob(JobCompletionListener listener, Step toClient) {
		return jobBuilderFactory.get("importClientJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(masterStep())
				.end()
				.build();
	}
	
	@Bean
	public Step toClient() {
		return stepBuilderFactory.get("toClient")
				.<Client, Client>chunk(chunkSize)	
				.reader(customReader)
				.processor(clientItemProcessor)
				.writer(customWriter)
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
				.partitioner("toClient", partitioner())
				.step(toClient())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	@StepScope
	@DependsOn("partitioner")
	public MultilineReader customReader(@Value("#{stepExecutionContext['fileName']}") String fileName) 
			throws MalformedURLException {
		MultilineReader reader = new MultilineReader();
		
		FlatFileItemReader<Client> delegate = 
				new FlatFileItemReaderBuilder<Client>()
					.name("clientItemReader")
					.resource(new UrlResource(fileName))
					.lineTokenizer(orderFileTokenizer())
					.fieldSetMapper(new ClientMapper())
					.linesToSkip(linesToSkip)
					.build();
		
		reader.setDelegate(delegate);
		
		return reader;
	}
	
	@Bean
	public PatternMatchingCompositeLineTokenizer orderFileTokenizer() {
	        PatternMatchingCompositeLineTokenizer tokenizer =
	                        new PatternMatchingCompositeLineTokenizer();

	        Map<String, LineTokenizer> tokenizers = new HashMap<>(5);

	        tokenizers.put("10*", clientTokenizer());
	        tokenizers.put("20*", clientTokenizer());
	        tokenizers.put("21*", bankAccountTokenizer());
	        tokenizers.put("22*", locationTokenizer());
	        tokenizers.put("99*", endLineTokenizer());
	        tokenizers.put("*", invalidDataTokenizer());

	        tokenizer.setTokenizers(tokenizers);

	        return tokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer clientTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(delimiter);
		tokenizer.setNames(new String[] {"code", "id", "name", "description", "address", "telephone", "identityNumber"});
		
		return tokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer bankAccountTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(delimiter);
		tokenizer.setNames(new String[] {"code", "id", "bankName", "accountNumber"});
		
		return tokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer locationTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(delimiter);
		tokenizer.setNames(new String[] {"code", "id", "codeName", "address", "post"});
		
		return tokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer endLineTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(delimiter);
		tokenizer.setNames(new String[] {"code", "info"});
		
		return tokenizer;
	}
	
	@Bean
	public DelimitedLineTokenizer invalidDataTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(delimiter);
		
		return tokenizer;
	}
	
}
