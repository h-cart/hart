package com.hart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

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
	public String productlist(@RequestParam(value = "pcno_top",required = false) int pcno_top, 
								@RequestParam(value ="pcno",required = false) int pcno, 
								Model model) {

		
		return "product/productList";

		
	}
	
	
}
