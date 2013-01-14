package com.online.filter.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AjaxAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication auth )
			throws IOException, ServletException{

		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || "true".equals(request.getHeader("X-Ajax-call"))) {
			response.getWriter().print("{ \"result\" :\"ok\" }");
			response.getWriter().flush();
		} else {
			super.onAuthenticationSuccess(request, response, auth);
		}
	}
}
