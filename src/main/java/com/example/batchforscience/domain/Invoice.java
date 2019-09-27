package com.example.batchforscience.domain;

import java.math.BigDecimal;

public class Invoice {

	private Long id;
	private Long client;
	private BigDecimal amount;
	private boolean paid;
	
	public Invoice() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public static Invoice of(BigDecimal amount, boolean paid) {
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		invoice.setPaid(paid);
		
		return invoice;
	}
	
	public static Invoice of(Long clientId, BigDecimal amount, boolean paid) {
		Invoice invoice = Invoice.of(amount, paid);
		invoice.setClient(clientId);
		
		return invoice;
	}
	
}
