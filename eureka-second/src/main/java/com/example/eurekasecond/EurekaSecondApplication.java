package com.example.eurekasecond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSecondApplication.class, args);
	}
}
