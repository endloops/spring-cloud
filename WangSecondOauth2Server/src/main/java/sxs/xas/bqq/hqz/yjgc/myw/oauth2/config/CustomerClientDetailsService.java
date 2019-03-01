package sxs.xas.bqq.hqz.yjgc.myw.oauth2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerClientDetailsService implements UserDetailsService {
    /***/
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerClientDetailsService.class);
    
    /***/
    @Autowired
    @Qualifier("objectMapper")
    private ObjectMapper om;
    
    private static final String AUTHORITIES = "authorities";
    
    private static final String TIMESTAMP = "timeStamp";
    
    private static final String CLIENTID = "clientId";
    
    private static final String VTAACCOUNT = "vtaAccount";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	if(username.split(",").length==3){
    		LOGGER.info("mysql zou le");
    		return new User(username, "password", true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER"));  
    	}else{
    		throw new UsernameNotFoundException("some exception set up");
    	}
    	
    }
}
