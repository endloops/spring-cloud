package sxs.xas.bqq.hqz.yjgc.myw.controller;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.impl.DemoServiceImplServiceLocator;
import sxs.xas.bqq.hqz.yjgc.myw.conf.webservice.impl.DemoServiceImplServiceSoapBindingStub;

@RestController
@RequestMapping("/test")
public class TestWebServiceCallController {
	
	@GetMapping
	@RequestMapping("/call")
	public void testCall() throws ServiceException, RemoteException{
		DemoServiceImplServiceLocator locator = new DemoServiceImplServiceLocator();
		DemoServiceImplServiceSoapBindingStub stub = (DemoServiceImplServiceSoapBindingStub) locator.getPort(DemoServiceImplServiceSoapBindingStub.class);
		Object object = stub.sayHello("???");
		System.out.println(object.toString());
	}
	
}
