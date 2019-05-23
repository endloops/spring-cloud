package sxs.xas.bqq.hqz.yjgc.myw.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class SocketHandshakeInterceptor implements ChannelInterceptor{
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		SimpMessageType type = accessor.getCommand().getMessageType();
		
		if(SimpMessageType.CONNECT.equals(type)){
			System.out.println("首次连接");
		}else if(SimpMessageType.DISCONNECT.equals(type)){
			System.out.println("断开连接");
		}
		
		
		return ChannelInterceptor.super.preSend(message, channel);
	}
}
