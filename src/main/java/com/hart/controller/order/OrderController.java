package com.hart.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hart.domain.order.OrderTotalDTO;
import com.hart.service.order.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService oService;
	
	@GetMapping("/qr")
	public void qrTest() {

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
		try {
			OrderTotalDTO oDTO = oService.getInfo(pids, pamounts);
			model.addFlashAttribute("oDTO",oDTO);
			System.out.println(oDTO);
		}catch (Exception e) {
			model.addFlashAttribute("msg", "ERROR");
			System.out.println(e.getMessage());
			url = "/error";
		}
		return "redirect:"+url;
	}

}
