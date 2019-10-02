package com.example.batchforscience.processor;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.service.ClientService;

@Component
public class ClientItemProcessor implements ItemProcessor<Client, Client> {
	
	@Autowired
	private ClientService clientService;

	@Override
	public Client process(Client item) throws Exception {
		
		StringBuilder fullName = new StringBuilder();
		fullName.append(item.getName()).append(" ").append(item.getDescription());	
		item.setFullName(fullName.toString());
		
		addDebtAndNumberOfOrders(item);
		
		return item;
	}

	private void addDebtAndNumberOfOrders(Client item) 
			throws InterruptedException, ExecutionException {

		ExecutorService pool = Executors.newFixedThreadPool(2);
		CompletableFuture<BigDecimal> debtCF = 
				CompletableFuture.supplyAsync(
						() -> clientService.getDebt(item.getId()), 
						pool);
		CompletableFuture<Long> ordersCF = 
				CompletableFuture.supplyAsync(
						() -> clientService.getNumberOfCurrentOrders(item.getId()), 
						pool);
		
		BigDecimal debt = debtCF.get();
		long currentOrders = ordersCF.get();
		item.setDebt(debt);
		item.setCurrentOrders(currentOrders);		
	}

	
}
