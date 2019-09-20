package com.example.batchforscience.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;

import com.example.batchforscience.TestConstants;
import com.example.batchforscience.domain.Client;
import com.example.batchforscience.mappers.ClientMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientMapperTest {
	
	@Test
	public void shouldReturnNull() throws BindException {
		String[] tokens = {};
		ClientMapper mapper = new ClientMapper();
		Client client = mapper.mapFieldSet(new DefaultFieldSet(tokens));

		assertThat(client).isNull();
	}
	
	@Test
	public void shouldReturnClientWithGivenValues() throws BindException {
		String[] tokens = {TestConstants.ID_1.toString(), TestConstants.NAME_1, 
				TestConstants.DESC_1, TestConstants.ADDR_1, TestConstants.TEL_1, TestConstants.IN_1};
		ClientMapper mapper = new ClientMapper();
		Client client = mapper.mapFieldSet(new DefaultFieldSet(tokens));

		assertThat(client.getId()).isEqualTo(TestConstants.ID_1);
		assertThat(client.getName()).isEqualTo(TestConstants.NAME_1);
	}

}
