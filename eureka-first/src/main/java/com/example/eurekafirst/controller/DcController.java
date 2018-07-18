package com.example.eurekafirst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() throws InterruptedException  {
//        Thread.sleep(50000l);
        String services = "Services: " + discoveryClient.getServices()+"first";
        System.out.println(services+"first");
        return services;
    }
}