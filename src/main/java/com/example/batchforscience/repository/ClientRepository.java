package com.example.batchforscience.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batchforscience.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
