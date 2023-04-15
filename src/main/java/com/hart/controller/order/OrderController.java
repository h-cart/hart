package com.hart.controller.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.OrderTotalDTO;
import com.hart.service.order.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService oService;
	
	@GetMapping("/qr")
	public void qr() {

	}


	@GetMapping("/result")
	public void result(@RequestParam(value = "pid", required = false) String pid, Model model) {
		if (pid != null)
			model.addAttribute("pid", pid);
	}
	
	@GetMapping("/checkout")
	public void showItems() {
		
	}
	@PostMapping("/list")
	public String list(@RequestParam("pids") List<String> pids, @RequestParam("pamounts") List<Integer> pamounts,RedirectAttributes model) {
		String url = "/order/checkout";
		System.out.println(pids);
		System.out.println(pamounts);
		try {
			OrderTotalDTO oDTO = oService.getInfo(pids, pamounts);
			model.addFlashAttribute("oDTO",oDTO);
		}catch (Exception e) {
			model.addFlashAttribute("msg", "ERROR");
			System.out.println(e.getMessage());
			url = "error";
		}
		return "redirect:"+url;
	}
	
	@PostMapping("/complete")
	public String insertOrder(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @ModelAttribute OrderInsertDTO insertDTO) {
		String url = "";
		try {
			insertDTO.getOinfo().setMid(mDTO.getMid());
			Map<String,Object> result = oService.insertOrder(mDTO,insertDTO);
			mDTO = (ClubAuthMemberDTO)result.get("mDTO");
			OinfoDTO oinfo = (OinfoDTO)result.get("oinfo");
			
			url = "/order/complete?oid="+oinfo.getOid();
		}catch (Exception e) {
		
			if(e.getMessage().equals("수강 중인 클래스 존재")) {
				url = "/error";
			}else
				url = "/cart/mycart";
			
			e.printStackTrace();
		}
		return "redirect:"+url;
	}
	
	@GetMapping("/complete")
	public String orderComplete(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @RequestParam("oid") String oid, Model model) {
		String url = "order/complete";
		try {
			
			model.addAttribute("oinfo", oService.getOrder(mDTO.getMid(), Integer.parseInt(oid)));
			
		}catch (Exception e) {
			model.addAttribute("msg", "오류 발생");
			url = "error";
		}
		
		return url;
		
	}

}
