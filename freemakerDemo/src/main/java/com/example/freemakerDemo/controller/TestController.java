package com.example.freemakerDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	

	@RequestMapping(path="/login.html")
	public String loginPage() {
		return "login";
	}
}
