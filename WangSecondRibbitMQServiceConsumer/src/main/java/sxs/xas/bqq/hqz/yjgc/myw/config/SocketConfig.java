package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import sxs.xas.bqq.hqz.yjgc.myw.utils.VtaStatus;


@Configuration
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {

	@Autowired
	SocketHandshakeInterceptor soss;
	
	/**
	 * 
	 * 注册stomp Endpoint
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
		.addEndpoint("/monitorEndpoint")
		.setAllowedOrigins("*")
		.setHandshakeHandler(new PrincipalDefaultHandshakeHandler())
		.withSockJS();
	}

	/**
	 * 配置消息代理信息.
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		// 线程池线程数，心跳连接开线程
		taskScheduler.setPoolSize(1);
		// 线程名前缀
		taskScheduler.setThreadNamePrefix("websocket-heartbeat-thread-");
		// 初始化
		taskScheduler.initialize();
		registry.enableSimpleBroker("/topic","/userTest");
		registry.setUserDestinationPrefix("/user")
//		.setHeartbeatValue(new long[] { 10000, 10000 })
//		.setTaskScheduler(taskScheduler)
				;
	}
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.taskExecutor().corePoolSize(3).maxPoolSize(3).keepAliveSeconds(60);
		registration.interceptors(soss);
			}
//	@Override
//	public void configureClientOutboundChannel(ChannelRegistration registration) {
//		// TODO Auto-generated method stub
//		registration.taskExecutor().corePoolSize(3).maxPoolSize(3).keepAliveSeconds(60);
//	}
	@Bean
	@Qualifier("messageHeader")
	public MessageHeaders initMessageHeaders() {
		Map<String, Object> header = new HashMap<String, Object>();
		header.put(MessageHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		return new MessageHeaders(header);
	}
	@Bean
	public Map<String, VtaStatus> users() {
		Map<String, VtaStatus> concurrentHashMap = new ConcurrentHashMap<>();
		return concurrentHashMap;
	}
	@Bean
	public Map<String, String> applications() {
		Map<String, String> applications = new ConcurrentHashMap<>();
		return applications;
	}
}
