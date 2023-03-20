package com.hart.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String getCart(@AuthenticationPrincipal ClubAuthMemberDTO mDTO,@RequestParam(value = "csno", required = false)String csno, @RequestParam(value = "cskey", required = false)String cskey,Model model) {
		String url = "";
		try {
			if(csno !=null && cskey!= null&& sService.getInfoWithKey(ShareDTO.builder()
					.csno(Integer.parseInt(csno))
					.cskey(cskey)
					.build())!=null) {
				model.addAttribute("csno", csno);
				model.addAttribute("cskey", cskey);
			}
		}catch (Exception e) {
			model.addAttribute("msg","fail");
		}
		
		
		if(mDTO.getCsno()==null) url = "/cart/mycart";
		else {
			url = "redirect:/cart/share/" + mDTO.getCsno();
		}
		return url;
	}

	@GetMapping("/group")
	public void joinShare(@RequestParam("cno") int cno, @RequestParam("key") String key, Model model) {
		try {

			ShareDTO sDTO = sService.getInfoWithKey(ShareDTO.builder().cskey(key).csno(cno).build());
			if (sDTO == null) {

			}

		} catch (Exception e) {

		}

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
			model.addAttribute("csno", csno);
		}
		return url;

	}


	@GetMapping("/checkout")
	public void checkout() {

	}

}
