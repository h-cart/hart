package com.hart.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.product.RecipeDetailVO;
import com.hart.service.RecipeService;

import lombok.extern.log4j.Log4j2;


/**
 * @since : 2023. 03. 25.
 * @FileName: RecipeController.java
 * @author : 박정훈
 * @설명 : 레시피 리스트 및 레시피 상세 컨트롤러
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 16.    박정훈       RecipeController 구현
 * 2023. 03. 19.    박정훈       recipe 페이지 구현
 * 2023. 04. 07.    박정훈       recipeDetail 페이지구현
 * 2023. 04. 11.    박정훈       recipe 작동 수정
 */

@Controller
@RequestMapping("/recipe")
@Log4j2
public class RecipeController {

	@Autowired
	RecipeService recipeservice;
	
	//레시피 목록 페이지
	@GetMapping("/list")
	public String recipelist(Model model) {
		try {
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "recipe/recipeList";
	}
	
	//레시피 상세 페이지
	@GetMapping("/recipeDetail")
	public String recipedetail(String rid, Model model) {
		try {
			RecipeDetailVO recipeDetail = recipeservice.recipeDetail(rid);
			model.addAttribute("recipeDetail", recipeDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "recipe/recipeDetails";
	}

}
