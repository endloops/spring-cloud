package docker.DockerDemo.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getName")
public class DockerDemoController {
	
	@Value("${user.home}")
	String home;
	
	@Value("${docker.config}")
	String ymlConfig;
	
	 @RequestMapping(method = RequestMethod.GET)
	 public String Demo(){
		return "javaBean:"+ home +"**************ymlConfig" + ymlConfig;		 
	}
}
