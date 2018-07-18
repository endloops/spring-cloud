package docker.DockerDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@PropertySource("file:${user.home}/mvtm/${spring.application.name}/appconfig.properties")
@SpringBootApplication
public class DockerDemoApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(DockerDemoApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}
}
