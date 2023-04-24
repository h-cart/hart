package com.hart.controller.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.share.ShareDTO;
import com.hart.service.share.ShareService;

/**
 * @since : 2023. 3. 14.
 * @FileName: CartController.java
 * @author : 남승현
 * @설명 : 장바구니 관련 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartController 구현
 * 2023. 3. 17.     남승현       공유 장바구니 기능추가에 따른 변경 
 *     </pre>
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ShareService sService;

	/* *Author : 남승현
	 * 기능 : 장바구니 페이지로 이동 
	 * 매개변수 : X
	 * 기타 : 로그인한 사용자에 대해, 공유 장바구니 번호가 존재하는 경우와 아닌 경우에 다른 페이지로 이동시킴 
	 */
	@GetMapping("/mycart")
	public String getCart(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		String url = "";
		if (mDTO.getCsno() == null)
			url = "cart/mycart";
		else {
			url = "redirect:/cart/share/" + mDTO.getCsno();
		}
		return url;
	}


	/* *Author : 남승현
	 * 기능 : 공유 장바구니 초대 페이지로 이동 
	 * 매개변수 : 공유 장바구니 번호, 공유 장바구니 비밀번호
	 * 기타 : 공유 장바구니 번호와 비밀번호가 일치하며, 
	 * 		 받은 사용자의 공유 장바구니 소지여부 및 소지한 아이디에 따라 다른 메세지를 파라미터로 보내줌
	 */
	@GetMapping("/group")
	public void joinShare(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @RequestParam(value = "csno") String csno,
			@RequestParam(value = "cskey") String cskey, Model model) {
		String msg = "";
		List<String> msgs = new ArrayList<>();
		int status = 0;
		try {
			if (csno != null && cskey != null && sService
					.getInfoWithKey(ShareDTO.builder().csno(Integer.parseInt(csno)).cskey(cskey).build()) != null) {
				model.addAttribute("csno", csno);
				model.addAttribute("cskey", cskey);
				if (mDTO.getCsno() == null) {
					msgs.add( "공유 장바구니로 쇼핑하기 요청을 수락하시겠습니까?");
					status = 2;
				} else if (mDTO.getCsno().equals(csno)) {
					msgs.add("동일한 장바구니 공유 요청을 받았습니다.");
					msgs.add("공유 장바구니 페이지로 이동합니다.");
					status = 1;
				} else if (!mDTO.getCsno().equals(csno)) {
					msgs.add("기존에 공유하던 장바구니가 존재합니다.");
					msgs.add("변경하시겠습니까?");
					status = 2;
				}
			} else {
				msgs.add("존재하지 않는 장바구니 공유 요청입니다.");
				status = -1;
			}
		} catch (Exception e) {
			msgs.add("장바구니 공유 요청 실패");
			status = -1;
		}
		model.addAttribute("status", status);
		model.addAttribute("msgs", msgs);
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 존재 시, 공유 장바구니 페이지로 이동시킴
	 * 매개변수 : 공유 장바구니 번호
	 * 기타 : 공유 장바구니 소지시, 공유 장바구니 페이지로 이동시킴
	 * 		 잘못되어 공유 장바구니가 없는 경우, error 페이지로 이동시킴
	 */
	@GetMapping("/share/{csno}")
	public String shareCart(@PathVariable("csno") int csno, Model model,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		String url = "";
		if (mDTO.getCsno() == null || Integer.parseInt(mDTO.getCsno()) != csno) {
			url = "error/share";

			model.addAttribute("msg", "허용되지 않은 접근입니다.");
		} else {
			url = "cart/share";
			try {
			} catch (Exception e) {
				model.addAttribute("msg", "잘못된 장바구니 정보입니다.");
				e.printStackTrace();
			}
		}
		return url;

	}
}
