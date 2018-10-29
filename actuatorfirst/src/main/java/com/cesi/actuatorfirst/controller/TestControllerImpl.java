package com.cesi.actuatorfirst.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/test")
public class TestControllerImpl {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(path="/wa")
	String getTest(TestBean testBean){
		logger.info("ss");
		return new File("applog/logs/").getAbsolutePath();
	}
	
	@RequestMapping(path="/log4j")
	String getlog4j(){
		logger.info("ss");
		logger.info("info test");

		logger.warn("warn test");

		logger.error("error test");

	    return "ok";
	}
}
