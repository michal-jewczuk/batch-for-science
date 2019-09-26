package com.example.batchforscience.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.ClientEntity;
import com.example.batchforscience.mappers.ClientEntityMapper;
import com.example.batchforscience.repository.ClientRepository;

@Component
public class ClientItemWriter implements ItemWriter<Client> {
	
	@Autowired
	private ClientEntityMapper mapper;
	
	@Autowired
	private ClientRepository repository;

	@Override
	public void write(List<? extends Client> items) throws Exception {
		@SuppressWarnings("unchecked")
		List<ClientEntity> entities = mapper.mapToEntities((List<Client>) items);
		repository.saveAll(entities);
	}

}
