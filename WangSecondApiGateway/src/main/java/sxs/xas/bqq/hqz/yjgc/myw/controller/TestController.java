package sxs.xas.bqq.hqz.yjgc.myw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sxs.xas.bqq.hqz.yjgc.myw.controller.bean.TestBean;

@RestController
@RequestMapping("/apis")
public class TestController {

	@ResponseBody
	@GetMapping("/get")
	public TestBean sera(){
		TestBean bean = new TestBean();
		bean.setName("'/");
		return  bean;
	}
}
