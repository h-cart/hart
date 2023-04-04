package com.hart.controller.member;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

	@GetMapping("/")
	public String home() {
		return "member/test";
	}
	

	@GetMapping("/login")
	public String login() {
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
		return "/admin";                                                                                                                                                                                                      
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
