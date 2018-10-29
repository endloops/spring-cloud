package com.example.freemakerDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class FreemakerDemoApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FreemakerDemoApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(FreemakerDemoApplication.class);
	}
}
