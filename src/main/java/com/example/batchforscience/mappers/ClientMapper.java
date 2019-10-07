package com.example.batchforscience.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.batchforscience.domain.Client;

public class ClientMapper implements FieldSetMapper<Client> {

	@Override
	public Client mapFieldSet(FieldSet fieldSet) throws BindException {	
		// should happen only with last line
		if (fieldSet.readString(0).equals("99")) {
			return null;
		}
		
		boolean isBusiness = fieldSet.readString(0).equals("10") ? false : true;
		
		Client client = new Client();
		client.setId(fieldSet.readLong(1));
		client.setName(fieldSet.readString(2));
		client.setDescription(fieldSet.readString(3));
		client.setAddress(fieldSet.readString(4));
		client.setTelephone(fieldSet.readString(5));
		client.setIdentityNumber(fieldSet.readString(6));
		client.setBusiness(isBusiness);
		
		return client;
	}

}
