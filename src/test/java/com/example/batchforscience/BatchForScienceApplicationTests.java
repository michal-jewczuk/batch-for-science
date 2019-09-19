package com.example.batchforscience;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfiguration.class})
public class BatchForScienceApplicationTests {
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void jobCompleted() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		
		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}

}
