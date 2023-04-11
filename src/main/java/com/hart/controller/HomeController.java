package com.hart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.product.MainProdVO;
import com.hart.domain.product.RecipeVO;
import com.hart.service.ProductsService;
import com.hart.service.RecipeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductsService productsservice;
	@Autowired
	private RecipeService recipeservice;
	
	@GetMapping("")
	public String home(Model model) {
		System.out.println("@@@@@@@@@");
		try {
		List<MainProdVO> mainprod = productsservice.getmainprod();
		List<RecipeVO> mainrecipe = recipeservice.mainrecipe();
		
		System.out.println("mainprod >>" + mainprod);
		model.addAttribute("mainprod", mainprod);
		model.addAttribute("mainrecipe", mainrecipe);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "index";
	}

	
	@GetMapping("/chat")
	public void chat() {

	}
}
