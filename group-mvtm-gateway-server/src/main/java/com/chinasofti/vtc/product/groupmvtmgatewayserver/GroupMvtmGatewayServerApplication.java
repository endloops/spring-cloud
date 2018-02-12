package com.chinasofti.vtc.product.groupmvtmgatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GroupMvtmGatewayServerApplication {

	public static void main(String[] args) {
		System.out.println("**********你是SB");
	    new SpringApplicationBuilder(GroupMvtmGatewayServerApplication.class).web(true).run(args);
	}
}
