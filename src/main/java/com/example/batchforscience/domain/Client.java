package com.example.batchforscience.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Client {

	private Long id;
	private String name;
	private String description;
	private String address;
	private String telephone;
	private String identityNumber;
	private String fullName;
	private BigDecimal debt;
	private long currentOrders;
	private boolean business;
	private Set<BankAccount> accounts;
	private Set<Location> locations;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public boolean isBusiness() {
		return business;
	}

	public void setBusiness(boolean business) {
		this.business = business;
	}

	public Set<BankAccount> getAccounts() {
		if (accounts == null) {
			accounts = new HashSet<BankAccount>();
		}
		return accounts;
	}

	public void setAccounts(Set<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public Set<Location> getLocations() {
		if (locations == null) {
			locations = new HashSet<Location>();
		}
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

}
