package com.hart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductCategorylistVO;
import com.hart.domain.ProductsDetailVO;
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
	public String productlist(Model model, int pcno) {

		List<CategoryVO> category = null;
		List<ProductsVO> productlist = null;
		// List<CategoryVO> categorypcno = null;
		List<ProductCategorylistVO> productcategorylist = null;
		try {
			category = productsservice.getcategorybar();
			// 왼쪽 카테고리 리스트
			productlist = productsservice.getproductslist(pcno);

			// categorypcno = productsservice.getcategorysmall(pcno);

			productcategorylist = productsservice.getproductcatrogrtlist(pcno);

			// System.out.println("productcategorylist Controller=========" +
			// productcategorylist);

			// categorysamll=productsservice.getcategorysmall(productsVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("productcategorylist", productcategorylist);
		// model.addAttribute("categorypcno", categorypcno);
		model.addAttribute("category", category);

		model.addAttribute("productlist", productlist);

		return "product/productList";
	}

	/*
	 * @GetMapping("/listpage") public String productlistpage(Model model,
	 * ProductsVO2 cvo) { List<ProductsVO2> category = null; try { category =
	 * productsservice.getcategoryproducts(cvo); } catch (Exception e) {
	 * e.printStackTrace(); } return "category"; }
	 */

	@GetMapping("/productDetail")
	public String ProductDtail(String pid, Model model) {

		try {

			ProductsDetailVO Detail = productsservice.getProductDetails(pid);

			// System.out.println("Detail ==================>>>>>>" + Detail);

			model.addAttribute("Detail", Detail);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/product/productDetail";

	}

}
