package com.hart.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.member.ClubAuthMemberDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private RequestCache requestCache; // RequestCache 주입

	
	@GetMapping("/")
	public String home() {
		return "member/test";
	}
	

	
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
		SavedRequest savedRequest = requestCache.getRequest(request, response); // RequestCache에서 요청 가져오기
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			model.addAttribute("targetUrl", targetUrl);
			System.out.println(">>>>>>>>>>>>>>" + targetUrl);
		}
		return "member/login";
	}

	@GetMapping("/modify")
	public void exmodify(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {
		log.info("exModify.....");
		log.info("--------------");
		log.info(clubAuthMemberDTO);
	}// end ex..

	@GetMapping("/admin")
	public String admin(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO, Model model) {

		model.addAttribute("user", clubAuthMemberDTO);
		model.addAttribute("roles", ((UsernamePasswordAuthenticationToken) clubAuthMemberDTO.getAuthorities()).getAuthorities());
		return "admin";                                                                                                                                                                                                      
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "member/mypage";
	}
	@GetMapping("/order-history")
	public String history() {
		return "member/mypage_order_history";
	}
	@GetMapping("/orderlist")
	public void getOrderList() {
	}
	

}
