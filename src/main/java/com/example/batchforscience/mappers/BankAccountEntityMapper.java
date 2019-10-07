package com.example.batchforscience.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.BankAccount;
import com.example.batchforscience.domain.entities.BankAccountEntity;

@Component
public class BankAccountEntityMapper {
	
	public BankAccountEntity mapToEntity(BankAccount account) {
		BankAccountEntity entity = new BankAccountEntity();
		entity.setId(account.getId());
		entity.setBankName(account.getBankName());
		entity.setAccountNumber(account.getAccountNumber());
		
		return entity;
	}
	
	public Set<BankAccountEntity> mapToEntities(Set<BankAccount> accounts) {
		return accounts.stream()
				.map(a -> mapToEntity(a))
				.collect(Collectors.toSet());
	}

}
