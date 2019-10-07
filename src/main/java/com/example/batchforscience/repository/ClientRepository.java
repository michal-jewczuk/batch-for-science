package com.example.batchforscience.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batchforscience.domain.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
}
