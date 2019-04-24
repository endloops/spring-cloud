package sxs.xas.bqq.hqz.yjgc.myw.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;

@Configuration
@EnableWebSecurity(debug = true)
public class Oauth2Security extends WebSecurityConfigurerAdapter {

	@Value("${securityldap.url}")
	String securityldapUrl;

	@Value("${securityldap.userDn}")
	String securityldapUserDn;

	@Value("${securityldap.password}")
	String securityldapPassword;

	@Value("${securityldap.group}")
	String securityldapGroup;

	@Value("${securityldap.userOu}")
	String securityldapUserOu;

	@Value("${securityldap.keystorename}")
	String keystorename;
	
	@Value("${securityldap.keystorepassword}")
	String keystorepassword;
	
	@Autowired
	private UserDetailsService clientDetailsUserDetailsService;

	/**
	 * 配置当前类的 AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("danan").password("danan")
				.authorities("ROLE_USER", "ROLE_READ", "ROLE_WRITE").and().withUser("mvtm").password("password")
				.authorities("ROLE_USER", "ROLE_READ", "ROLE_WRITE").and().and()
			.userDetailsService(customerClientDetailsService()).and()//从数据库取用户名，密码
			.authenticationProvider(LdapAuthenticationProvider())//从ladp服务器取出来，用户名密码
			.eraseCredentials(false);
		// .ldapAuthentication().contextSource().url("ldaps://192.168.89.253:3269/dc=ad,dc=service,dc=com").managerDn("system").managerPassword("password").
		// and().userDnPatterns("CN={0},cn=Users").groupSearchBase("ou=application").groupSearchFilter("cn");
	}

	

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		System.setProperty("javax.net.ssl.trustStore", keystorename);
		System.setProperty("javax.net.ssl.keyStorePassword", keystorepassword);
		DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(securityldapUrl);
		contextSource.setUserDn(securityldapUserDn);
		contextSource.setPassword(securityldapPassword);
		return contextSource;
	}

	@Bean
	public BindAuthenticator bindAuthenticator() {
		BindAuthenticator authenticator = new BindAuthenticator(contextSource());
		String param = new String("CN={0},");
		String[] dnPattern = new String[] { param + securityldapUserOu };
		authenticator.setUserDnPatterns(dnPattern);
		return authenticator;
	}
	@Bean
	public DefaultLdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
		DefaultLdapAuthoritiesPopulator authoritiesPopulator = new DefaultLdapAuthoritiesPopulator(contextSource(),
				securityldapGroup);
		authoritiesPopulator.setGroupRoleAttribute("cn");
		authoritiesPopulator.setSearchSubtree(true);
		return authoritiesPopulator;
	}
	@Bean
	public LdapAuthenticationProvider LdapAuthenticationProvider() {
		LdapAuthenticationProvider authenticationProvider = new LdapAuthenticationProvider(bindAuthenticator(),
				ldapAuthoritiesPopulator());
		return authenticationProvider;
	}

	@Bean
	public CustomerClientDetailsService customerClientDetailsService() {
		return new CustomerClientDetailsService();
	}

	@Bean
	public UserDetailsService clientDetailsUserDetailsService(ClientDetailsService clientDetailsService) {
		return new ClientDetailsUserDetailsService(clientDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/login", "/login.html", "/logout", "/oauth/authorize").and()
				.authorizeRequests().antMatchers("/login.html", "/login").permitAll().and().formLogin()
				.loginPage("/login.html").loginProcessingUrl("/login")
				.failureUrl("/login.html?authentication_error=true").and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/logout.html").and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER).and().httpBasic()
				.realmName("authorizationServer");

	}

	/**
	 * export authentication manager object for password grant model 将当前配置类的
	 * AuthenticationManagerBuilder 放入 AuthenticationManager 中
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}
}
