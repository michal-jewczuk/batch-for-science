package com.example.batchforscience.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "telephone", nullable = false)
	private String telephone;

	@Column(name = "identity_number", nullable = false)
	private String identityNumber;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "full_name", nullable = true)
	private String fullName;
	
	@Column(name = "debt", nullable = true)
	private BigDecimal debt;
	
	@Column(name = "current_orders", nullable = true)
	private long currentOrders;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BigDecimal getDebt() {
		return debt;
	}

	public void setDebt(BigDecimal debt) {
		this.debt = debt;
	}

	public long getCurrentOrders() {
		return currentOrders;
	}

	public void setCurrentOrders(long currentOrders) {
		this.currentOrders = currentOrders;
	}

	@Override
	public String toString() {
		String hasDebt = debt.longValue() > 0 ? "yes" : "no";
		return "ClientEntity [id=" + id + ", name=" + name + ", address=" + address + ", telephone=" + telephone
				+ ", identityNumber=" + identityNumber + ", description=" + description + ", fullName=" + fullName
				+ ", hasDebt=" + hasDebt + ", currentOrders=" + currentOrders + "]";
	}

}
