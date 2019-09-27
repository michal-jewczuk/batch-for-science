package com.example.batchforscience.service;

import java.util.List;

import com.example.batchforscience.domain.Invoice;
import com.example.batchforscience.domain.Order;

public interface HttpService {

	List<Invoice> getClientInvoices(Long clientId);
	
	List<Order> getClientOrders(Long clientId);
}
