package com.example.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.HeaderMap;

@FeignClient(name = "EUREKA-CUSTOMER")
public interface FeignService {
	 @RequestMapping(value = "/getName",method=RequestMethod.GET)
	 String getName(@RequestHeader Map map);
}
