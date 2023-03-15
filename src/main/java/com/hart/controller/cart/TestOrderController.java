package com.hart.controller.cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class TestOrderController {
	
	
	@GetMapping("/qr")
	public void qrTest() {
		
	}
	
	@GetMapping("/result")
	public void result(@RequestParam(value = "pid", required = false)String pid, Model model) {
		if(pid != null) model.addAttribute("pid", pid);
	}
	
	
}
