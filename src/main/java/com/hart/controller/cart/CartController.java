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

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ShareService sService;

	@GetMapping("/mycart")
	public String getCart(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		String url = "";
		if (mDTO.getCsno() == null)
			url = "/cart/mycart";
		else {
			url = "redirect:/cart/share/" + mDTO.getCsno();
		}
		return url;
	}

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

	@GetMapping("/share/{csno}")
	public String shareCart(@PathVariable("csno") int csno, Model model,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		String url = "";
		if (mDTO.getCsno() == null || Integer.parseInt(mDTO.getCsno()) != csno) {
			url = "/error/share";

			model.addAttribute("msg", "허용되지 않은 접근입니다.");
		} else {
			url = "/cart/share";
			try {
				model.addAttribute("sDTO", sService.getInfo(mDTO.getMid()));
			} catch (Exception e) {
				model.addAttribute("msg", "잘못된 장바구니 정보입니다.");
				e.printStackTrace();
			}
		}
		return url;

	}

	

	@GetMapping("/checkout")
	public void checkout() {

	}

}
