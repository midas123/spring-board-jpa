package com.yk.web.springsecurity;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException exception) throws IOException, ServletException {
		Enumeration<String> names = req.getAttributeNames();
		while(names.hasMoreElements()) {
			System.out.println("names:"+names.nextElement());
		}
		
		if(exception instanceof BadCredentialsException) {
			res.sendRedirect("/login?error=fail1");
		}
		
		if(exception instanceof UsernameNotFoundException) {
			res.sendRedirect("/login?error=fail2");
		}

	}

}
