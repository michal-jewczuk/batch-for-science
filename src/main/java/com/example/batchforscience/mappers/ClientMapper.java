package com.example.batchforscience.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.batchforscience.domain.Client;

public class ClientMapper implements FieldSetMapper<Client> {

	@Override
	public Client mapFieldSet(FieldSet fieldSet) throws BindException {	
		// should happen only with last line
		if (fieldSet.getFieldCount() < 2) {
			return null;
		}
		
		Client client = new Client();
		client.setFirstName(fieldSet.readString(0));
		client.setLastName(fieldSet.readString(1));
		client.setDescription(fieldSet.readString(2));
		client.setAddress(fieldSet.readString(3));
		client.setTelephone(fieldSet.readString(4));
		client.setIdentityNumber(fieldSet.readString(5));
		
		return client;
	}

}
