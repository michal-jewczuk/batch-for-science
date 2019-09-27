package com.example.batchforscience.mock.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batchforscience.mock.domain.InvoiceEntity;
import com.example.batchforscience.mock.repository.InvoiceRepository;

@RestController
@RequestMapping("/mock")
public class InvoiceRestController {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@GetMapping("/invoices")
	public List<InvoiceEntity> showAllInvoices() {
		return invoiceRepository.findAll();
	}
	
	@GetMapping("/invoices/{clientId}")
	public List<InvoiceEntity> showAllInvoices(@PathVariable Long clientId) {
		return invoiceRepository.findAllByClient(clientId);
	}

}
