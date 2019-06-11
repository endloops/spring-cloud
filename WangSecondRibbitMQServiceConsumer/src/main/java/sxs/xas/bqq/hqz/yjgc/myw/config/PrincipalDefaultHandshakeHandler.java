package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class PrincipalDefaultHandshakeHandler extends DefaultHandshakeHandler{
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(PrincipalDefaultHandshakeHandler.class);
	
	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		

        HttpSession httpSession = getSession(request);
        // 获取登录的信息，就是controller 跳转页面存的信息，可以根据业务修改
        String user = (String)httpSession.getAttribute("loginName");
        HttpHeaders header = request.getHeaders();
//        List<String> user = header.get("username");
        
        if(StringUtils.isEmpty(user)){
            log.error("未登录系统，禁止登录websocket!");
            return null;
        }
        log.info(" MyDefaultHandshakeHandler login = " + user);
        return new WebSocketUserAuthentication(user);
//        return super.determineUser(request, wsHandler, attributes);
    }
	private HttpSession getSession(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession(false);
        }
        return null;
    }
}
