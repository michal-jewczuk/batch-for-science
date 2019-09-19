package com.example.batchforscience;

import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.example.batchforscience.config.BatchConfiguration;

@Configuration
@EnableAutoConfiguration
@Import({BatchConfiguration.class})
@PropertySource("classpath:application.yml")
public class TestConfiguration {
  
    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

}