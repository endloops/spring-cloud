package sxs.xas.bqq.hqz.yjgc.myw.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/oauth")
public class MVTMClientApi {
    
    private static final String ACCESS_TOKEN="access_token";
    
    
    public static final String VTMTOKEN ="ENCRYPT-VTM-CLIENT-INFO";
    
    
    @Autowired
    @Qualifier("trustOauth2RestTemplate")
    private OAuth2RestTemplate trustOauth2RestTemplate; 

    /***/
    private static Logger logger = LoggerFactory.getLogger(MVTMClientApi.class);
    /**
     * OMS login
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/token")
    public Map<String, ?> login(HttpServletResponse response , HttpServletRequest request) throws Exception {
        OAuth2AccessToken token = null;
        HashMap<String, Object> map = null;
        try {
        	
        	Enumeration<String> ssss = request.getHeaderNames();
    		while(ssss.hasMoreElements()){
    			System.out.println(ssss.nextElement());
    		}
    		System.out.println(request.getRemoteAddr());
    		System.out.println(request.getRemoteHost());
            token = trustOauth2RestTemplate.getAccessToken();
            map = new HashMap<String,Object>();
            map.put("additionInfo", token.getAdditionalInformation());
            map.put("expiration", token.getExpiration());
            map.put("access_token", token.getValue());
            map.put("refresh_token", token.getRefreshToken().getValue());
        } catch (OAuth2AccessDeniedException e) {
        	
           logger.error("-----Oauth2Exception---",e);
        } catch (Exception e) {
        	logger.error("-----unException---",e);
           throw new Exception(e.getMessage() , e);
		}
        return map;
    }
    @RequestMapping(path = "/validation")
    public Map<String, ?> validation(HttpServletRequest request) throws Exception {
        HashMap<String, Object> map = null;
        ObjectMapper mapper = null;
        Cookie[] allTokens = request.getCookies();
            for(Cookie token : allTokens){
                if(token.getName().equals(ACCESS_TOKEN)){
                    logger.info("this request have AccessToken {}",token.getValue());
                    Jwt jwt=JwtHelper.decodeAndVerify(token.getValue(), new MacSigner("secert"));
                    map = new HashMap<>();
                    String str=jwt.getClaims();
                    mapper = new ObjectMapper();
                    map=mapper.readValue(str, HashMap.class);
                    Date nowDate = new Date();
                    Date tikenDeadline = new Date(Long.parseLong(map.get("exp").toString())*1000l);
                    if(tikenDeadline.after(nowDate)){
                        return map;
                    }else{
                        throw new Exception("access_token is More than the deadline");
                    }
                }
            }
            logger.error("all cookies select but not find access_token");
            throw new Exception("cookie not have access_token");
    }
}
