package com.example.batchforscience.domain;

import java.time.LocalDate;

public class Order {

	private Long id;
	private Long client;
	private LocalDate readyOn;
	
	public Order() {
		
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

	public LocalDate getReadyOn() {
		return readyOn;
	}

	public void setReadyOn(LocalDate readyOn) {
		this.readyOn = readyOn;
	}
	
	public static Order of(LocalDate readyOn) {
		Order order = new Order();
		order.setReadyOn(readyOn);
		
		return order;
	}
	
}
