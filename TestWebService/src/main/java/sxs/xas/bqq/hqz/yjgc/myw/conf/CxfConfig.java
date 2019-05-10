package sxs.xas.bqq.hqz.yjgc.myw.conf;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.DemoService;
import sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.impl.DemoServiceImpl;

@Configuration
public class CxfConfig {
	/**
	 * 启动CXFServlet 查看当前所有的已启动的webService服务
	 * http://localhost:8989/demo
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<CXFServlet> diServletRegistrationBean(){
		return new ServletRegistrationBean<CXFServlet>(new CXFServlet(),"/demo/*");
	}
	@Bean(name=Bus.DEFAULT_BUS_ID)
	public SpringBus springBus(){
		return new SpringBus();
	}
	
	@Bean
	public DemoService demoService(){
		return new DemoServiceImpl();
	}
	/**
	 * 发布服务
	 * 将DemoService 服务发布为
	 * http://localhost:8989/demo/api?wsdl
	 * @return
	 */
	@Bean
	public Endpoint endpoint(){
		EndpointImpl endpointImpl = new EndpointImpl(springBus(), demoService());
		endpointImpl.publish("/api");
		return endpointImpl;
	}
}
