package com.cesi.actuatorfirst;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author David
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("file:applog/logs/")
		.setCacheControl(CacheControl.maxAge(30, TimeUnit.HOURS).cachePrivate());
		super.addResourceHandlers(registry);
	}

}
