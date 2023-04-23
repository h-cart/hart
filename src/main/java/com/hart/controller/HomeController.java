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


/**
 * @since : 2023. 04. 8.
 * @FileName: HomeController.java
 * @author : 박정훈
 * @설명 : 메인페이지 상품 및 레시피 추천 컨트롤러
 * 
 */
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

}
