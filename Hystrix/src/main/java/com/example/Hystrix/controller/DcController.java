package com.example.Hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DcController {

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/first")
    public String dc() {
        return consumerService.first();
    }
    @GetMapping("/second")
    public String dcs() {
        return consumerService.second();
    }

}
