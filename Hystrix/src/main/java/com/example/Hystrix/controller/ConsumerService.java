package com.example.Hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public  class ConsumerService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackFirst")
    public String first() {
        return restTemplate.getForObject("http://eureka-first/dc", String.class);
    }

    public String fallbackFirst() {
        return "first-fallback";
    }
    
    @HystrixCommand(fallbackMethod = "fallbackSecond")
    public String second() {
        return restTemplate.getForObject("http://eureka-second/dc", String.class);
    }

    public String fallbackSecond() {
        return "second-fallback";
    }
}
