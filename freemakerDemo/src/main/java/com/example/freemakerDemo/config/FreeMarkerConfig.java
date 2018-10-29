package com.example.freemakerDemo.config;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class FreeMarkerConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ContentNegotiatingViewResolver contentViewResolver() {
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
		contentNegotiationManager.addMediaType("html", MediaType.TEXT_HTML);

		MappingJackson2JsonView jsonview = new MappingJackson2JsonView();
		jsonview.setExtractValueFromSingleKeyModel(true);

		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver("", ".ftl");
		freeMarkerViewResolver.setExposeRequestAttributes(true);
		freeMarkerViewResolver.setExposeSessionAttributes(true);
		freeMarkerViewResolver.setRequestContextAttribute("request");
		freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
		ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
		contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		contentViewResolver.setViewResolvers(Arrays.<ViewResolver> asList(freeMarkerViewResolver));
		contentViewResolver.setDefaultViews(Arrays.<View> asList(jsonview));
		return contentViewResolver;
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

		super.configureHandlerExceptionResolvers(exceptionResolvers);
	}

//	@Bean
//	@Primary
//    public CustomerTokenStore customerTokenStore() {
//        return new CustomerTokenStore(dataSource);
//    }
		
	@Bean
	public FreeMarkerConfigurer freeMarkerConfigure(){
		FreeMarkerConfigurer freeMarkerConfigure = new FreeMarkerConfigurer();
		freeMarkerConfigure.setTemplateLoaderPath("/WEB-INF/templates/");
		Properties p = new Properties();
		p.setProperty("template_exception_handler", "html_debug");
		//p.setProperty("defaultEncoding", "UTF-8");
		freeMarkerConfigure.setFreemarkerSettings(p);
		return freeMarkerConfigure;
	}
}

