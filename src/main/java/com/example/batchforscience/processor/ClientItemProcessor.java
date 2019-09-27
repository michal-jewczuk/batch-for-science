package com.example.batchforscience.processor;

import java.math.BigDecimal;

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
		BigDecimal debt = clientService.getDebt(item.getId());	
		long currentOrders = clientService.getNumberOfCurrentOrders(item.getId());
		
		item.setFullName(fullName.toString());
		item.setDebt(debt);
		item.setCurrentOrders(currentOrders);
		
		return item;
	}

}
