package com.example.batchforscience.writer;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.entities.ClientEntity;
import com.example.batchforscience.mappers.ClientEntityMapper;
import com.example.batchforscience.repository.ClientRepository;

@Component
public class ClientItemWriter implements ItemWriter<Client> {
	
	@Autowired
	private ClientEntityMapper mapper;
	
	@Autowired
	private ClientRepository repository;

	@Override
	@Transactional
	public void write(List<? extends Client> items) throws Exception {
		@SuppressWarnings("unchecked")
		List<ClientEntity> entities = mapper.mapToEntities((List<Client>) items);
		repository.saveAll(entities);
	}

}
