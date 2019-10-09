package com.example.batchforscience.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccountEntity {

	@Id
	private Long id;

	@Column(name = "bank_name", nullable = false)
	private String bankName;

	@Column(name = "account_number", nullable = false)
	private String accountNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "BankAccountEntity [id=" + id + ", bankName=" + bankName + ", accountNumber=" + accountNumber + "]";
	}

}
