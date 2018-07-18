package com.example.feign;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
	
	@Autowired
	FeignService feign;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getFeign() {
		Map<String, String> map = new HashMap<>();
		map.put("eta", "aa");
		map.put("sdsd", "asdsd");
		return feign.getName(map);
	}
}
