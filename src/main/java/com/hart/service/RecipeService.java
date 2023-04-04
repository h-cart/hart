package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.product.RecipeDetailVO;
import com.hart.domain.product.RecipeVO;

@Service
public interface RecipeService {

	
	public List<RecipeVO> recipelist(RecipeVO recipelist) throws Exception;
		
	
	public RecipeDetailVO recipeDetail(String rid) throws Exception;



}
