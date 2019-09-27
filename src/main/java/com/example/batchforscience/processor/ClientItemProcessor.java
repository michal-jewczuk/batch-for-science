package com.example.batchforscience.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.service.ClientService;

public class ClientItemProcessor implements ItemProcessor<Client, Client> {
	
	@Autowired
	private ClientService clientService;

	@Override
	public Client process(Client item) throws Exception {
		
		StringBuilder fullName = new StringBuilder();
		fullName.append(item.getName()).append(" ").append(item.getDescription());		
		item.setFullName(fullName.toString());
		
		return item;
	}

}
