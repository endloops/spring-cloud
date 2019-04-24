package sxs.xas.bqq.hqz.yjgc.myw.config;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
public class ResourceSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**
	 * 这里用了yml里面的配置
	 * security:
  			oauth2:
    			resource:
      					jwt:
        				key-value: secret
                     实例化一个 DefaultTokenServices
       	如果没有 配置：
       	默认实例化 RemoteTokenServices。会报错 除非你去实例化
	 */
	@Autowired
	private ResourceServerTokenServices resoureServerTokenServices;
	
	 @Bean
	    @Qualifier("clientContext")
	    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
	    public OAuth2ClientContext clientContext(
	            @Autowired @Qualifier("accessTokenRequest") AccessTokenRequest accessTokenRequest , HttpServletRequest httpRequest) {
	    	HttpSession session=httpRequest.getSession(false);
			
			String token=extractHeaderToken(httpRequest);
			if(token!=null) {
				return  new DefaultOAuth2ClientContext(new DefaultOAuth2AccessToken(token));
			}
			
			if(session!=null) {
				Object clientContext = session.getAttribute("clientContext");
				if(clientContext!=null) {
					return (OAuth2ClientContext) clientContext;
				}else{
					clientContext=new DefaultOAuth2ClientContext(accessTokenRequest);
					session.setAttribute("clientContext",clientContext);
					return (OAuth2ClientContext) clientContext;
				}
			}
			
	        return new DefaultOAuth2ClientContext(accessTokenRequest);
	    }
	 protected String extractHeaderToken(HttpServletRequest request) {
			Enumeration<String> headers = request.getHeaders("Authorization");
			while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
				String value = headers.nextElement();
				if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
					String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
					// Add this here for the auth details later. Would be better to change the signature of this method.
					request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
							value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
					int commaIndex = authHeaderValue.indexOf(',');
					if (commaIndex > 0) {
						authHeaderValue = authHeaderValue.substring(0, commaIndex);
					}
					return authHeaderValue;
				}
			}

			return null;
		}
	
	@Bean
	@Primary
	public OAuth2ProtectedResourceDetails resourceService() {
		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("product-resource-service");
		details.setClientId("api-gateway");
		details.setClientSecret("{noop}password");
		details.setAccessTokenUri("http://localhost:8234/oauth/token");
		details.setUserAuthorizationUri("http://localhost:8234/oauth/authorize");
		details.setScope(Arrays.asList("read", "write"));
		details.setUseCurrentUri(true);
		return details;
	}

	@Bean
	@Primary
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext clientContext) {
		return new OAuth2RestTemplate(resourceService(), clientContext);
	}
	
	@Bean
	public AccessTokenProvider userAccessTokenProvider() {
		ResourceOwnerPasswordAccessTokenProvider accessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
		return accessTokenProvider;
	}
	
	
	@Bean
	public UserInfoRestTemplateFactory userInfoRestTemplateFactory(@Autowired @Qualifier("oauth2RestTemplate") final OAuth2RestTemplate trustOauth2RestTemplate) {
		return new UserInfoRestTemplateFactory(){

			@Override
			public OAuth2RestTemplate getUserInfoRestTemplate() {
				
				return trustOauth2RestTemplate;
			}
			
		};
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.debug(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ProductPreOauth2ResourceFilter authenticationProcessingFilter = new ProductPreOauth2ResourceFilter();
		
		OAuth2AuthenticationManager authManager = new OAuth2AuthenticationManager();
		authManager.setTokenServices(resoureServerTokenServices);
		authenticationProcessingFilter.setAuthenticationManager(authManager);
		authenticationProcessingFilter.setAuthenticationDetailsSource(new OAuth2AuthenticationDetailsSource());

//		 http.addFilterAfter(authenticationProcessingFilter, SecurityContextPersistenceFilter.class).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
//	        .logout().logoutSuccessUrl("/").and().formLogin().and()
//	            .authorizeRequests()
//	                .antMatchers("/index.html", "/app.html", "/", "/login","/trust/**","/error").permitAll()
//	                .anyRequest().authenticated().and().csrf().disable()
//	             ;

				http.requestMatchers()
				.antMatchers("/oauth2/**","/login", "/management/**", "/transfer/**", "/logout", "/user/login","/apis/**",
						"/irecorder/**" ,"/ceshi" ,"/zuul/**").and().addFilterAfter(authenticationProcessingFilter, SecurityContextPersistenceFilter.class).
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
	            .authorizeRequests()
//	                .antMatchers("/oauth2/oauth/token").permitAll()
	                .anyRequest().authenticated().and().csrf().disable().logout().logoutSuccessUrl("/irecorder/index.html")
	                ;
		//irecorder/index.html
	            // @formatter:on
	}
	
}
