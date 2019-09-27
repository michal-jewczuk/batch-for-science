package com.example.batchforscience.mock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batchforscience.mock.domain.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findAllByClient(Long clientId);
}
