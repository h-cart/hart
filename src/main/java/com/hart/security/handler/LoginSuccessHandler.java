package com.hart.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.share.SseEmitters;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private final SseEmitters sseEmitters;
	
	// 구성자 추가 SecurityConfig 에서 사용
	public LoginSuccessHandler(PasswordEncoder passwordEncoder, SseEmitters sseEmitters) {
		this.passwordEncoder = passwordEncoder;
		this.sseEmitters = sseEmitters;
	}

	// RedirectStrategy 인터페이스 생성 sendRedirect() 메서드 이용
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	// 사용자 암호 확인 용
	private PasswordEncoder passwordEncoder;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("------------------------");
		log.info("onAuthenticationSuccess");
		
		HttpSession session = request.getSession();
		// 인증 객체에서 사용자 정보 저장
		ClubAuthMemberDTO clubAuthMemberDTO = (ClubAuthMemberDTO) authentication.getPrincipal();
		if(clubAuthMemberDTO.getCsno()!=null) {
			SseEmitter emitter = new SseEmitter(60 * 60 * 60L);
			sseEmitters.add(clubAuthMemberDTO.getCsno(),emitter,clubAuthMemberDTO);
		}
		log.info(clubAuthMemberDTO);
		// 소셜 사용자인지 확인
		int fromSocial = clubAuthMemberDTO.getSocial();
		// 사용자 암호 1111 인지 확인
		boolean passresult = passwordEncoder.matches("1111", clubAuthMemberDTO.getMpassword());
		
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response); 
		String targetUrl ="/";
		if (savedRequest!=null) {
			 targetUrl = savedRequest.getRedirectUrl();
		}
		// 소셜 사용자이고 암호 1111이면 modify.html 페이지로 이동
		if ((fromSocial == 1) && passresult) {
			
			//redirectStrategy.sendRedirect(request, response, "/member/modify?social=social");
			redirectStrategy.sendRedirect(request, response,targetUrl);
		} // end if

	}// end onAu…

}// end class
