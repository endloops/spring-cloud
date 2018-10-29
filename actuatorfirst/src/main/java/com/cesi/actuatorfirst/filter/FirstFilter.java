package com.cesi.actuatorfirst.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@WebFilter(filterName="firstFilter" , urlPatterns="/**")
@Order(1)
public class FirstFilter implements Filter{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("进来");
		logger.info("进来"); 
		HttpServletRequest hrequest = (HttpServletRequest)arg0;
		 MyParamRequestWrapper parameterRequestWrapper = new MyParamRequestWrapper(hrequest);
		 parameterRequestWrapper.addParameter("ss", "哇嘎嘎");
		arg2.doFilter(parameterRequestWrapper, arg1);
		System.out.println("结束");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
