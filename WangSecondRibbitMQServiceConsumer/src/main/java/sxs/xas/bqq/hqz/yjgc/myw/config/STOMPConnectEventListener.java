//package sxs.xas.bqq.hqz.yjgc.myw.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.web.socket.messaging.SessionConnectedEvent;
//
//public class STOMPConnectEventListener implements ApplicationListener<SessionConnectedEvent> {
//
//	@Autowired
//	SocketSessionRegistry webAgentSessionRegistry;
//
//	@Override
//	public void onApplicationEvent(SessionConnectedEvent event) {
//		StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
//		// login get from browser
//		String agentId = sha.getNativeHeader("login").get(0);
//		String sessionId = sha.getSessionId();
//		webAgentSessionRegistry.registerSessionId(agentId, sessionId);
//	
//	}
//
//}
