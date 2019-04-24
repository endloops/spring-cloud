package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@Configuration
@EnableOAuth2Client
public class Oauth2ClientConfig {

   
//    protected String extractHeaderToken(HttpServletRequest request) {
//		Enumeration<String> headers = request.getHeaders("Authorization");
//		while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
//			String value = headers.nextElement();
//			if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
//				String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
//				// Add this here for the auth details later. Would be better to change the signature of this method.
//				request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
//						value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
//				int commaIndex = authHeaderValue.indexOf(',');
//				if (commaIndex > 0) {
//					authHeaderValue = authHeaderValue.substring(0, commaIndex);
//				}
//				return authHeaderValue;
//			}
//		}
//
//		return null;
//	}
    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
    public ResourceOwnerPasswordResourceDetails trustResourceService(
    		@Value("#{request.getParameter('" + "refresh_token" + "')}") String refreshToken,
    		@Value("#{request.getParameter('" + "grant_type" + "')}") String grantType,
            @Value("#{request.getParameter('" + "username" + "')}") String username,
            @Value("#{request.getParameter('" + "password" + "')}") String password)   {
        ResourceOwnerPasswordResourceDetails details = null;
        if(username != null && password != null){
            details = new ResourceOwnerPasswordResourceDetails();
            details.setId("product-resource-service");
            details.setClientId("api-gateway");
            details.setClientSecret("{noop}password");
            details.setAccessTokenUri("http://localhost:8234/oauth/token");
            details.setScope(Arrays.asList("read", "write"));
            details.setGrantType("password");
            if(username != null && password != null){
                details.setUsername(username);
                details.setPassword(password);
                return details;
            }else{
                return null;
            }
        }else if(refreshToken != null && grantType != null){
        	details = new ResourceOwnerPasswordResourceDetails();
            details.setId("product-resource-service");
            details.setClientId("api-gateway");
            details.setClientSecret("secret");
            details.setAccessTokenUri("http://localhost:8080/oauth/token");
            details.setScope(Arrays.asList("ROLE_OMS", "ROLE_AO"));
            return details;
        }
        return null;
    }
    
    @Bean
    public OAuth2RestTemplate trustOauth2RestTemplate(
            @Qualifier("trustResourceService") OAuth2ProtectedResourceDetails trustResourceService,
            OAuth2ClientContext clientContext) throws Exception {
        OAuth2RestTemplate oauth = new OAuth2RestTemplate(trustResourceService, clientContext);
        ResourceOwnerPasswordAccessTokenProvider passwordAccessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
        //ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(clientFactory().getObject());
        //passwordAccessTokenProvider.setRequestFactory(requestFactory);
        oauth.setAccessTokenProvider(passwordAccessTokenProvider);
        return oauth;
    }

}
