package com.hart.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.product.RecipeContentVO;
import com.hart.domain.product.RecipeDetailVO;
import com.hart.domain.product.RecipeVO;
import com.hart.mapper.RecipeMapper;

import lombok.extern.log4j.Log4j2;


/**
 * @since : 2023. 03.28.
 * @FileName: ProductsService.java
 * @author : 박정훈
 * @설명 : 레시피 리스트 및 상세 서비스
 * 
 */
@Log4j2
@Service
public class RecipeServiceImple implements RecipeService{

	@Autowired
	RecipeMapper recipemapper;
	
	@Override
	public List<RecipeVO> recipelist(RecipeVO recipelist) {
		return recipemapper.recipelist(recipelist);
	}
	
	
	@Override
	public RecipeDetailVO recipeDetail(String rid) {
			
			RecipeVO recipedetailtop = recipemapper.recipeDetail(rid);
			List<RecipeContentVO> Rcontent = recipemapper.RecipeContent(rid);
			return new RecipeDetailVO(recipedetailtop, Rcontent);
	}
	
	@Override
	public List<RecipeVO> mainrecipe(){
		return recipemapper.mainrecipe();
	}
	
}
