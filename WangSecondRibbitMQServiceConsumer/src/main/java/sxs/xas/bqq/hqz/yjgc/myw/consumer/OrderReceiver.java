package sxs.xas.bqq.hqz.yjgc.myw.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import sxs.xas.bqq.hqz.yjgc.myw.entity.Order;

@Component
public class OrderReceiver {

	@RabbitListener(bindings = @QueueBinding(
			value=@Queue(value="order-queue",durable="true"),
			exchange=@Exchange(name="order-exchange",durable="true",type="topic"),
			key="order.*")
	)
	@RabbitHandler
	public void onMessage(@Payload Order order,Channel channel,@Headers Map<String, Object> headers) throws Exception{
		//消费者操作
		System.out.println("收到消息");
		System.out.println("订单 ID "+order.getId());
		Long deliverTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
		//ACK
		channel.basicAck(deliverTag, false);
	}
}
