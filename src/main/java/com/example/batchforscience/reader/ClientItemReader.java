package com.example.batchforscience.reader;

import java.net.MalformedURLException;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.UrlResource;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.mappers.ClientMapper;


public class ClientItemReader {

	public static FlatFileItemReader<Client> reader(String fileName, String delimiter, int linesToSkip) 
			throws MalformedURLException {
		
		DefaultLineMapper<Client> lineMapper = new DefaultLineMapper<Client>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer(delimiter));
		lineMapper.setFieldSetMapper(new ClientMapper());

		return new FlatFileItemReaderBuilder<Client>().name("clientItemReader")
				.resource(new UrlResource(fileName))
				.delimited()
				.names(new String[] {"id", "name", "description", "address", "telephone", "identityNumber"})
				.lineMapper(lineMapper)
				.linesToSkip(linesToSkip)
				.build();
	}
}
