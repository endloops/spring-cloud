package com.example.feign.config;

import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;


public class TokenAuthRequestInterceptor implements RequestInterceptor{



	public  TokenAuthRequestInterceptor (){
		
	}

	@Override
	public void apply(RequestTemplate template) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		System.out.println(authentication);
		template.header("Authorization", "ss");
	}


}
