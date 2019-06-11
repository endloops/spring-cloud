package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatus;

@Component
public class SocketHandshakeInterceptor implements ChannelInterceptor{
	
	@Autowired
	Map<String, VtaStatus> users;
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		SimpMessageType type = accessor.getCommand().getMessageType();

		if(SimpMessageType.CONNECT.equals(type)){
			//simpSessionAttributes={}, simpHeartbeat=[J@10c5ca46, simpSessionId=f1lwu5mw}
			String username = accessor.getNativeHeader("username").get(0);
            String password = accessor.getNativeHeader("password").get(0);
            if(users.get(username)!=null){
            	users.replace(username, VtaStatus.READY);
            }else{
            	users.put(username,  VtaStatus.READY);
            }
            System.out.println("首次连接");
			if (username!=null && password!=null){
                Principal principal = new Principal() {
                    @Override
                    public String getName() {
                        return username;
                    }
                };
                accessor.setUser(principal);
                return message;
            }else {
                return null;
            }
		}else if(SimpMessageType.DISCONNECT.equals(type)){
			System.out.println("断开连接");
		}
		
		
		return ChannelInterceptor.super.preSend(message, channel);
	}
}
