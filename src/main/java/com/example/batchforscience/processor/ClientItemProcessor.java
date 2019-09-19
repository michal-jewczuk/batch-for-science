package com.example.batchforscience.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.ClientEntity;

public class ClientItemProcessor implements ItemProcessor<Client, ClientEntity> {
	
	private static final Logger log = LoggerFactory.getLogger(ClientItemProcessor.class);

	@Override
	public ClientEntity process(Client item) throws Exception {
		ClientEntity entity = new ClientEntity();
		
		StringBuilder fullName = new StringBuilder();
		fullName.append(item.getFirstName()).append(" ").append(item.getLastName());
		
		entity.setId(item.getId());
		entity.setFullName(fullName.toString());
		entity.setDescription(item.getDescription());
		entity.setAddress(item.getAddress());
		entity.setTelephone(item.getTelephone());
		entity.setIdentityNumber(item.getIdentityNumber());
		
		log.info(entity.toString());
		return entity;
	}

}
