package com.example.batchforscience.mock.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {

	@Id
	private Long id;

	@Column(name = "client_id", nullable = false)
	private Long client;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "is_paid", nullable = false)
	private boolean paid;

	public InvoiceEntity() {

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

}
