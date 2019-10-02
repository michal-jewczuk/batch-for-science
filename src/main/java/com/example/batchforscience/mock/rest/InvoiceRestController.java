package com.example.batchforscience.mock.rest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.batchforscience.mock.MockConfig;
import com.example.batchforscience.mock.domain.InvoiceEntity;
import com.example.batchforscience.mock.repository.InvoiceRepository;

@RestController
@RequestMapping("/mock/invoices")
public class InvoiceRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceRestController.class);
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@GetMapping("")
	public List<InvoiceEntity> showAllInvoices() {
		return invoiceRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public List<InvoiceEntity> showAllClientInvoices(@PathVariable Long clientId) {
		logger.info("=== INVOICES FOR " + clientId);
		try {
			TimeUnit.MILLISECONDS.sleep(MockConfig.DELAY);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invoiceRepository.findAllByClient(clientId);
	}

}
