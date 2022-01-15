package com.controlestoque;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
//import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
//		servletContext.getSessionCookieConfig().setMaxAge(0); // limite de tempo logado
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));

	FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encondingFilter", new CharacterEncodingFilter());
	characterEncodingFilter.setInitParameter("encoding", "UTF-8");
	characterEncodingFilter.setInitParameter("encoding", "UTF-8");
	characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
