package com.example.batchforscience.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
	private Long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "telephone", nullable = false)
	private String telephone;

	@Column(name = "identity_number", nullable = false)
	private String identityNumber;

	@Column(name = "description", nullable = false)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	@Override
	public String toString() {
		return "ClientEntity [id=" + id + ", fullName=" + fullName + ", address=" + address + ", telephone=" + telephone
				+ ", identityNumber=" + identityNumber + ", description=" + description + "]";
	}

}
