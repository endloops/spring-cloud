package sxs.xas.bqq.hqz.yjgc.myw;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sxs.xas.bqq.hqz.yjgc.myw.entity.model.Order;
import sxs.xas.bqq.hqz.yjgc.myw.entity.mapper.OrderMapper;
import sxs.xas.bqq.hqz.yjgc.myw.producer.OrderSender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class WangSecondRibbitMqServiceApplicationTests {

	@Autowired
	private OrderSender sender;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	public void contextLoads() {
	}
	
//	@Test
//	public void testSender()throws Exception{
//		sxs.xas.bqq.hqz.yjgc.myw.entity.Order order = new sxs.xas.bqq.hqz.yjgc.myw.entity.Order();
//		order.setId("20190520");
//		order.setName("测试订单1");
//		order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID().toString());
//		sender.send(order);
//	}
	
	@Test
	public void testInsert()throws Exception{
		Order order = new Order();
		order.setId("20190520");
		order.setName("测试订单1");
		order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID().toString());
		orderMapper.insert(order);
	}
}
