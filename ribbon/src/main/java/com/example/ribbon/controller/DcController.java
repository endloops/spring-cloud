package com.example.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/first")
    public String dcFirst() {
        return restTemplate.getForObject("http://eureka-first/dc", String.class);
    }
    @GetMapping("/second")
    public String dcSecond() {
        return restTemplate.getForObject("http://eureka-second/dc", String.class);
    }
}
