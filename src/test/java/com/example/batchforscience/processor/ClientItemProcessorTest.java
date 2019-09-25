package com.example.batchforscience.processor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.batchforscience.TestConstants;
import com.example.batchforscience.domain.Client;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientItemProcessorTest {
	
	@Test
	public void shouldReturnClientEntity() throws Exception {
		Client client = createClient2();
		ClientItemProcessor processor = new ClientItemProcessor();
		Client entity = processor.process(client);
		
		assertThat(entity.getId()).isEqualTo(TestConstants.ID_2);
		assertThat(entity.getFullName()).isEqualTo(TestConstants.NAME_2 + " " + TestConstants.DESC_2);
	}

	private Client createClient2() {
		Client client = new Client();
		client.setId(TestConstants.ID_2);
		client.setName(TestConstants.NAME_2);
		client.setDescription(TestConstants.DESC_2);
		client.setAddress(TestConstants.ADDR_2);
		client.setTelephone(TestConstants.TEL_2);
		client.setIdentityNumber(TestConstants.IN_2);
		return client;
	}

}
