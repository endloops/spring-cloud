package sxs.xas.bqq.hqz.yjgc.myw.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sxs.xas.bqq.hqz.yjgc.myw.entity.mapper.OrderMapper;
import sxs.xas.bqq.hqz.yjgc.myw.entity.model.Order;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderMapper orderMapper;
	
	
	@GetMapping("/insert")
	public void insertOrder(){
		Order order = new Order();
		order.setId("20190520");
		order.setName("测试订单1");
		order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID().toString());
		orderMapper.insert(order);

	}
}
