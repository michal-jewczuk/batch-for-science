package com.example.batchforscience.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.Client;
import com.example.batchforscience.domain.ClientEntity;

@Component
public class ClientEntityMapper {

	ClientEntity mapToEntity(Client client) {
		ClientEntity entity = new ClientEntity();
		entity.setId(client.getId());
		entity.setName(client.getName());
		entity.setFullName(client.getFullName());
		entity.setDescription(client.getDescription());
		entity.setAddress(client.getAddress());
		entity.setIdentityNumber(client.getIdentityNumber());
		entity.setTelephone(client.getTelephone());
		
		return entity;
	}
	
	List<ClientEntity> mapToEntities(List<Client> clients) {
		List<ClientEntity> entities = new ArrayList<>();
		entities.addAll(clients.stream()
				.map(e -> mapToEntity(e))
				.collect(Collectors.toList())
				);
		
		return entities;
	}
}
