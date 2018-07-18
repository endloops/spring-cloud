package com.example.eurekacustomer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaCustomer1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaCustomer1Application.class, args);
	}
}
