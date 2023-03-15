package com.hart.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hart.service.share.ShareService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ShareService sService;
	
	
	@GetMapping("/mycart")
	public void getCart() {
		
	}
	
	@GetMapping("/group")
	public void shareCart(@RequestParam("cno")String cno, @RequestParam("key")String key) {
		
		
	}
}
