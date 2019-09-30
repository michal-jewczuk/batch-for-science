package com.example.batchforscience.processor;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
		
		//computeConcurrently(item);		
		computeWithExecutorPool(item);
		//computeWithCompletableFuture(item);
		
		return item;
	}
	
	private void computeConcurrently(Client item) {
		BigDecimal debt = clientService.getDebt(item.getId());	
		long currentOrders = clientService.getNumberOfCurrentOrders(item.getId());
		item.setDebt(debt);
		item.setCurrentOrders(currentOrders);	
	}
	
	private void computeWithExecutorPool(Client item) throws InterruptedException, ExecutionException {
		//runtime with DELAY 900 is 6.4 s
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		Future<BigDecimal> debts = pool.submit(new Callable<BigDecimal>() {
		    @Override
		    public BigDecimal call() 
		    	{return clientService.getDebt(item.getId());}
		});	
		
		Future<Long> orders = pool.submit(new Callable<Long>() {
		    @Override
		    public Long call() 
		    	{return clientService.getNumberOfCurrentOrders(item.getId());}
		});

		BigDecimal debt = debts.get();
		long currentOrders = orders.get();
		item.setDebt(debt);
		item.setCurrentOrders(currentOrders);
	}

	private void computeWithCompletableFuture(Client item) throws InterruptedException, ExecutionException {
		//runtime with DELAY 900 is 8.1 s
		CompletableFuture<BigDecimal> debtCF = CompletableFuture.supplyAsync(() -> clientService.getDebt(item.getId()));
		CompletableFuture<Long> ordersCF = CompletableFuture.supplyAsync(() -> clientService.getNumberOfCurrentOrders(item.getId()));
		CompletableFuture<Void> combinedFuture  = CompletableFuture.allOf(debtCF, ordersCF);
		combinedFuture.get();
		
		BigDecimal debt = debtCF.get();
		long currentOrders = ordersCF.get();
		item.setDebt(debt);
		item.setCurrentOrders(currentOrders);		
	}

}
