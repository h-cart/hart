package com.hart.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		setUseForward(true);
		setDefaultFailureUrl("/member/login?error=true");
		request.setAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
}// end class
}