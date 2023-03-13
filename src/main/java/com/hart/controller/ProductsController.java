package com.hart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.ProductsVO;
import com.hart.service.ProductsService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/product")
public class ProductsController {

	@Autowired
	private ProductsService productsservice;

	@GetMapping("/list")
	public String productlist(ProductsVO productsVO,Model model) {
		
		log.info("-------------products-------------1. = "+productsVO);
		
		List<ProductsVO> products = null;
		try {
			
			products = productsservice.getProductList(productsVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("-------------products-------------2");
		
		model.addAttribute("products",products);
		return "product/productList";
	}

}
