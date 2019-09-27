package com.example.batchforscience.mock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batchforscience.mock.domain.InvoiceEntity;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

	List<InvoiceEntity> findAllByClient(Long clientId);
	
}
