package com.example.eurekacustomer1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/getName")
    public String dc() throws InterruptedException  {
//        Thread.sleep(50000l);
        String services = "Services: " + discoveryClient.getServices()+"customer1";
        System.out.println(services+"first");
        throw new InterruptedException("aaa");
        //return services;
    }
}