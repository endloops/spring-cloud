package com.example.feign.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.filter.RequestContextFilter;

import com.netflix.ribbon.proxy.annotation.Http.Header;

import feign.Contract;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FooConfiguration {

	
    @Bean
    public TokenAuthRequestInterceptor basicAuthRequestInterceptor() throws  IOException, HttpException {

    	return new TokenAuthRequestInterceptor();
    }
}