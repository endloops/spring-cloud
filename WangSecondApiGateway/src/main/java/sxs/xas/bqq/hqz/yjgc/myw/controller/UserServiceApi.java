package sxs.xas.bqq.hqz.yjgc.myw.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis")
public class UserServiceApi {

	@Autowired
    @Qualifier("oauth2RestTemplate")
    private OAuth2RestTemplate restTemplate; 
	
	@RequestMapping("/fetch")
	public String fetchResponse() throws InterruptedException, ExecutionException {
//		return new UserServiceHystrixCommand().queue().get();
		return "ss";
	}
	
	
	@RequestMapping("/users")
	public Map<String,?> getUser() {
		OAuth2AccessToken accessToken=restTemplate.getAccessToken();
		Jwt jwt = JwtHelper.decode(accessToken.getValue());
		String claims = jwt.getClaims();
		accessToken.getTokenType();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tokenType", accessToken.getTokenType());
		map.put("userName", null);
		map.put("expiration", accessToken.getExpiration().getTime());
		map.put("access_token", accessToken.getValue());
		
		return map;
	}
	
}
