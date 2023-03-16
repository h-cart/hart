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
	public String getCart(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		String url = "";
		if(mDTO.getCsno()==null) url = "/cart/mycart";
		else {
			url = "redirect:/cart/share/"+mDTO.getCsno();
		}
		return url;
	}
	
	@GetMapping("/group")
	public void joinShare(@RequestParam("cno")String cno, @RequestParam("key")String key,Model model) {
		try {
			ShareDTO sDTO =  sService
		}catch (Exception e) {
			
		}
		
	}
	
	@GetMapping("/share/{csno}")
	public String shareCart(@PathVariable("csno") int csno,Model model, @AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		model.addAttribute("csno", csno);
		return "/cart/share";
		
	}
	
	@GetMapping("/checkout")
	public void checkout() {
		
	}
	
}
