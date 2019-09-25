package com.example.batchforscience.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.batchforscience.domain.Client;

public class ClientItemProcessor implements ItemProcessor<Client, Client> {
	
	private static final Logger log = LoggerFactory.getLogger(ClientItemProcessor.class);

	@Override
	public Client process(Client item) throws Exception {
		Client client = new Client();
		
		StringBuilder fullName = new StringBuilder();
		fullName.append(item.getName()).append(" ").append(item.getDescription());
		
		client.setId(item.getId());
		client.setName(item.getName());
		client.setDescription(item.getDescription());
		client.setAddress(item.getAddress());
		client.setTelephone(item.getTelephone());
		client.setIdentityNumber(item.getIdentityNumber());
		client.setFullName(fullName.toString());
		
		log.info(client.toString());
		return client;
	}

}
