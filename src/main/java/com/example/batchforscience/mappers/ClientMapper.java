package com.example.batchforscience.mappers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.batchforscience.domain.BankAccount;
import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.Location;

public class ClientMapper implements FieldSetMapper<Client> {
	
	//private static final String TOKEN_FIRST_LINE = "01";
	private static final String TOKEN_LAST_LINE = "99";
	private static final String TOKEN_CLIENT_NONB = "10";
	private static final String TOKEN_CLIENT_B = "20";
	private static final String TOKEN_BANK_ACCOUNT = "21";
	private static final String TOKEN_LOCATION = "22";
	

	@Override
	public Client mapFieldSet(FieldSet fieldSet) throws BindException {	
		String prefix = fieldSet.readString(0);
			
		Client client = new Client();
		
		switch (prefix) {
			case TOKEN_CLIENT_NONB:
			case TOKEN_CLIENT_B:
				mapToClient(client, fieldSet);
				break;
			case TOKEN_BANK_ACCOUNT:
				client.setAccounts(readAccount(fieldSet));
				break;
			case TOKEN_LOCATION:
				client.setLocations(readLocation(fieldSet));
				break;
			case TOKEN_LAST_LINE:
				return null;
			default:
				//should be something for error handling
				System.out.println("===== Nobody expects Spanish Inquisition! =====");
		}

		return client;
	}

	private void mapToClient(Client client, FieldSet fieldSet) {
		client.setId(fieldSet.readLong(1));
		client.setName(fieldSet.readString(2));
		client.setDescription(fieldSet.readString(3));
		client.setAddress(fieldSet.readString(4));
		client.setTelephone(fieldSet.readString(5));
		client.setIdentityNumber(fieldSet.readString(6));
		
		boolean isBusiness = fieldSet.readString(0).equals(TOKEN_CLIENT_NONB) ? false : true;
		client.setBusiness(isBusiness);
	}

	private Set<BankAccount> readAccount(FieldSet fieldSet) {
		Set<BankAccount> accounts = new HashSet<BankAccount>();
		BankAccount account = new BankAccount();
		account.setId(fieldSet.readLong(1));
		account.setBankName(fieldSet.readString(2));
		account.setAccountNumber(fieldSet.readString(3));
		accounts.add(account);
		return accounts;
	}

	private Set<Location> readLocation(FieldSet fieldSet) {
		Set<Location> locations = new HashSet<Location>();
		return locations;
	}
}
