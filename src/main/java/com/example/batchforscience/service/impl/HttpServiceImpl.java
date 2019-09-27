package com.example.batchforscience.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Invoice;
import com.example.batchforscience.domain.Order;
import com.example.batchforscience.service.HttpService;

@Component
public class HttpServiceImpl implements HttpService {

	private static final String INVOICE_URI = "http://localhost:8080/invoices/";
	private static final String ORDER_URI = "http://localhost:8080/orders/";
	
	public List<Invoice> getClientInvoices(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Order> getClientOrders(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
