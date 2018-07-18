package com.example.eurekacustomer.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/getName")
    public String dc(HttpServletRequest request) throws InterruptedException  {
//        Thread.sleep(50000l);
    	Enumeration<String> s = request.getHeaderNames();
    	while(s.hasMoreElements()){
    		String ss = s.nextElement();
    		String sss = request.getHeader(ss);
    		System.out.println(ss+":"+sss);
    	}
    	String services = "Services: " + discoveryClient.getServices()+"customer";
//        + discoveryClient.getServices()+"customer";
        System.out.println(services+"first");
        return services;
    }
}