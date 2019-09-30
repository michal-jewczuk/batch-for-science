package com.example.batchforscience.mock.rest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batchforscience.mock.MockConfig;
import com.example.batchforscience.mock.domain.OrderEntity;
import com.example.batchforscience.mock.repository.OrderRepository;

@RestController
@RequestMapping("/mock/orders")
public class OrderRestController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("")
	public List<OrderEntity> getAllOrders() {
		return orderRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public List<OrderEntity> showAllClientOrders(@PathVariable Long clientId) {
		try {
			TimeUnit.MILLISECONDS.sleep(MockConfig.DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderRepository.findAllByClient(clientId);
	}

}
