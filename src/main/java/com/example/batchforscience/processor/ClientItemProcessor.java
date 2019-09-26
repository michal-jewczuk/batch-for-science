package com.example.batchforscience.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.batchforscience.domain.Client;

public class ClientItemProcessor implements ItemProcessor<Client, Client> {

	@Override
	public Client process(Client item) throws Exception {
		
		StringBuilder fullName = new StringBuilder();
		fullName.append(item.getName()).append(" ").append(item.getDescription());		
		item.setFullName(fullName.toString());
		
		return item;
	}

}
