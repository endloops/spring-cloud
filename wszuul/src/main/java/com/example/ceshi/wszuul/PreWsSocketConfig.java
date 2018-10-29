package com.example.ceshi.wszuul;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class PreWsSocketConfig implements GlobalFilter, Ordered {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return Ordered.LOWEST_PRECEDENCE - 2;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		 logger.info("first pre filter");
         ServerHttpRequest ss = null;
         ServerWebExchange bus = null;
         MultiValueMap<String, HttpCookie> cookie = exchange.getRequest().getCookies();
         HttpHeaders  requestHeader= exchange.getRequest().getHeaders();
         String accessToken = requestHeader.getFirst("VTM-TOKEN-KEY");
         ServerHttpRequest request = exchange.getRequest();
         if (cookie == null & accessToken == null) {
             bus = exchange;
         } else if(accessToken != null){
             
             String userName = null;
             
             Jwt jwt = JwtHelper.decode(accessToken);
             try {
                 JSONObject jsonObj = new JSONObject(jwt.getClaims().toString());
                 userName = jsonObj.getString("user_name");
             } catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             //requestHeader.add("aa", "Bearer" + " " + accessToken);
             //requestHeader.add("client_information", userName);
             ss =  exchange.getRequest().mutate()
                     .header("Authorization", "Bearer" + " " + accessToken)
                     .header("client_information", userName)
                   .build();
             bus = exchange.mutate().request(ss).build();
             logger.info("(C#)pass the client_information {}", userName); 
         }else if(cookie != null) {
                 if (cookie.containsKey("access_token")) {
                     String userName = null;
                     logger.info("request {} add Header(Authorization)", exchange.getRequest().getURI().toString());
                     // 兼容AO Project，pass client_information header
                     List<HttpCookie> token = cookie.get("access_token");
                     Jwt jwt = JwtHelper.decode(token.get(0).getValue());
                     Map<String, Object> claims = null;
                     try {
                         JSONObject jsonObj = new JSONObject(jwt.getClaims().toString());
                         userName = jsonObj.getString("user_name");
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                    
//                     requestHeader.add("client_information", userName);
//                   requestHeader.add("Authorization", "Bearer" + " " + cookie.get("access_token"));

                     ss =  exchange.getRequest().mutate()
                             .header("Authorization", "Bearer" + " " + accessToken)
                             .header("client_information", userName)
                           .build();
                     bus = exchange.mutate().request(ss).build();
                     logger.info("(FrontSide)pass the client_information {}", userName);
             }else{
                 bus = exchange;
             }
         }
         return chain.filter(bus).then(Mono.fromRunnable(() -> {
             exchange.getResponse().getHeaders().add("aa", "aaa");
          }));
	}

}
