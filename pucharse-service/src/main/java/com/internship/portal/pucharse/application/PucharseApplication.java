package com.internship.portal.pucharse.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan("com.internship.portal")
public class PucharseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PucharseApplication.class, args);
	}

}
