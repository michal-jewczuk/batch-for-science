package com.example.batchforscience.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.batchforscience.domain.Invoice;
import com.example.batchforscience.domain.Order;
import com.example.batchforscience.service.HttpService;

@Component
public class HttpServiceImpl implements HttpService {

	private static final String INVOICE_URI = "http://localhost:8080/mock/invoices/";
	private static final String ORDER_URI = "http://localhost:8080/mock/orders/";
	private RestTemplate restTemplate = new RestTemplate();
	
	public List<Invoice> getClientInvoices(Long clientId) {
		ParameterizedTypeReference<List<Invoice>> typeRef = new ParameterizedTypeReference<List<Invoice>>() {};
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {
			ResponseEntity<List<Invoice>> response = restTemplate.exchange(INVOICE_URI + clientId, HttpMethod.GET, null, typeRef);
			invoices = response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return invoices;
	}
	
	public List<Order> getClientOrders(Long clientId) {
		ParameterizedTypeReference<List<Order>> typeRef = new ParameterizedTypeReference<List<Order>>() {};
		List<Order> orders = new ArrayList<Order>();
		try {
			ResponseEntity<List<Order>> response = restTemplate.exchange(ORDER_URI + clientId, HttpMethod.GET, null, typeRef);
			orders = response.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return orders;
	}
	
}
