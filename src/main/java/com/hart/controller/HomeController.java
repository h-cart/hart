package com.hart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hart.domain.share.ShareDTO;
import com.hart.service.share.ShareService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/")
public class HomeController {

	public String home() {
		return "index";
	}
	
	@GetMapping("/chat")
	public void chat() {
		
	}
}
