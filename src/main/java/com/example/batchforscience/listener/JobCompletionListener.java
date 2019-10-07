package com.example.batchforscience.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.batchforscience.domain.entities.ClientEntity;
import com.example.batchforscience.repository.ClientRepository;

@Component
public class JobCompletionListener extends JobExecutionListenerSupport {
	
	private static final Logger log = LoggerFactory.getLogger(JobCompletionListener.class);

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("==== Listing clients before job ====");
		listClients();
	}


	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("==== Listing clients after job ====");
		listClients();
	}
	
	private void listClients() {
		List<ClientEntity> entities = clientRepository.findAll();
		if (entities.isEmpty()) {
			log.info(">>> Database is empty");
		} else {
			for (ClientEntity client: entities) {
				log.info(client.toString());
			}
		}
	}

}
