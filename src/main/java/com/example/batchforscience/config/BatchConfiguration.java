package com.example.batchforscience.config;

import java.io.IOException;
import java.net.MalformedURLException;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.listener.JobCompletionListener;
import com.example.batchforscience.processor.ClientItemProcessor;
import com.example.batchforscience.reader.ClientItemReader;
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
	private FlatFileItemReader<Client> clientItemReader;
	
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
				.reader(clientItemReader)
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
	public FlatFileItemReader<Client> clientReader(@Value("#{stepExecutionContext['fileName']}") String filename)
			throws MalformedURLException {
		return ClientItemReader.reader(filename, delimiter, linesToSkip);
	}      
	
}
