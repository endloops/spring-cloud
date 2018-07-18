package com.example.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//@Configuration
//@EnableWebSecurity
//@EnableResourceServer
public class Oauth2ResourceServiceConfig extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //OAuth2AuthenticationProcessingFilter
       // resources.authenticationEntryPoint(loanAuthenticationEntryPoint());
        resources.resourceId("oms-resource-service").tokenServices(tokenService());
        //resources.accessDeniedHandler(loanAccessTokenExpiredEntryPoint());
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.authorizeRequests()
        .antMatchers("/**").permitAll();
       // .antMatchers("/**").permitAll().anyRequest().hasRole("INFODIR-HACN-OMS-USERS");
        http.exceptionHandling();
    }
    
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }


    @Bean
    public JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("secert");

        return jwtAccessTokenConverter;
    }

    
    @Bean
    public ResourceServerTokenServices tokenService() {
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(jwtTokenStore());
        return tokenService;
    }
    

            
}
