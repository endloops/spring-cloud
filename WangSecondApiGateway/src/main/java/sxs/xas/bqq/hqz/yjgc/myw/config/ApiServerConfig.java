package sxs.xas.bqq.hqz.yjgc.myw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class ApiServerConfig  extends WebMvcConfigurerAdapter{
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/irecorder/**")
                .addResourceLocations("file:C:\\Users\\wang\\Desktop\\")
                .setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("/**")
                .addResourceLocations("/");
    }

	@Bean public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	} 
}
