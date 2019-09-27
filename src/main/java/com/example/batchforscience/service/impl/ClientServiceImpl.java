package com.example.batchforscience.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Invoice;
import com.example.batchforscience.domain.Order;
import com.example.batchforscience.service.ClientService;
import com.example.batchforscience.service.HttpService;

@Component
public class ClientServiceImpl implements ClientService {
	
	private HttpService httpService;
	
	@Autowired
	public ClientServiceImpl(HttpService httpService) {
		this.httpService = httpService;
	}

	public BigDecimal getDebt(Long clientId) {
		List<Invoice> invoices = httpService.getClientInvoices(clientId);
		BigDecimal debt = invoices.stream()
				.filter(i -> !i.isPaid())
				.map(i -> i.getAmount())
				.reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
		
		return debt;
	}
	
	public long getNumberOfCurrentOrders(Long clientId) {
		List<Order> orders = httpService.getClientOrders(clientId);
		LocalDate today = LocalDate.now();
		
		return orders.stream()
				.filter(o -> o.getReadyOn().isAfter(today))
				.count();
	}
}
