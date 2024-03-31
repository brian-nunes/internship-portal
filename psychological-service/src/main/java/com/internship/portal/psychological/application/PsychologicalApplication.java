package com.internship.portal.psychological.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan("com.internship.portal")
@EntityScan(basePackages = "com.internship.portal.psychological.model")
@EnableJpaRepositories(basePackages = "com.internship.portal.psychological.repository")
public class PsychologicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsychologicalApplication.class, args);
	}

}
