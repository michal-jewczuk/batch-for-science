package com.example.batchforscience.mock.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

	@Id
	private Long id;

	@Column(name = "client_id", nullable = false)
	private Long client;

	@Column(name = "ready_on", nullable = false)
	private LocalDate readyOn;

	public OrderEntity() {

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

}
