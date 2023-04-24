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

/**
 * @since : 2023. 03. 14.
 * @FileName: MemberController.java
 * @author : 이승규
 * @설명 : 로그인/ 회원가입기능 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 14.     이승규       login 구현
 * 2023. 03. 14.     이승규       admin 구현
 * 2023. 03. 27.     이승규       mypage 구현
 * 2023. 03. 27.     함세강       history 구현
 *     </pre>
 */
@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private RequestCache requestCache; // RequestCache 주입



	// 로그인 페이지
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
		SavedRequest savedRequest = requestCache.getRequest(request, response); // RequestCache에서 요청 가져오기
		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			model.addAttribute("targetUrl", targetUrl);
		}
		return "member/login";
	}

	//관리자 권한
	@GetMapping("/admin")
	public String admin(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO, Model model) {

		model.addAttribute("user", clubAuthMemberDTO);
		model.addAttribute("roles", ((UsernamePasswordAuthenticationToken) clubAuthMemberDTO.getAuthorities()).getAuthorities());
		return "admin";                                                                                                                                                                                                      
	}
	
	// 마이페이지 이동
	@GetMapping("/mypage")
	public String mypage() {
		return "member/mypage";
	}

	// 마이페이지 - 주문내역 페이지
	@GetMapping("/order-history")
	public String history() {
		return "member/mypage_order_history";
	}

	// 마이페이지 - 주문내역 상세 페이지
	@GetMapping("/orderlist")
	public void getOrderList() {
	}
	
	
	

}
