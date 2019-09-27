package com.example.batchforscience.service;

import java.math.BigDecimal;

public interface ClientService {
	
	BigDecimal getDebt(Long clientId);
	
	long getNumberOfCurrentOrders(Long clientId);

}
