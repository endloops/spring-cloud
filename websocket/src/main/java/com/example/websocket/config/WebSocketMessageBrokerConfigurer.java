package com.example.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		registry.addEndpoint("/websocket-simple")
        .setAllowedOrigins("*") // 添加允许跨域访问
        .withSockJS();
		
	}
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * 配置消息代理
         * 启动简单Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
         */
		registry
		// "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
                .enableStompBrokerRelay("/exchange")
                .setRelayHost("localhost")
                .setRelayPort(5672)
//                .setClientLogin("guest")
//                .setClientPasscode("guest")
//                .setSystemLogin("guest")
//                .setSystemPasscode("guest")
//                .setSystemHeartbeatSendInterval(5000)
//                .setSystemHeartbeatReceiveInterval(4000)
                ;

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        super.configureClientInboundChannel(registration);
    }
}
