package sxs.xas.bqq.hqz.yjgc.myw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication (exclude= {SecurityAutoConfiguration.class,SecurityFilterAutoConfiguration.class})
@EnableZuulProxy
public class WangSecondApiGatewayApplication  extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WangSecondApiGatewayApplication.class, args);
	}
}
