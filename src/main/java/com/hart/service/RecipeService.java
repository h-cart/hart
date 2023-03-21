package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.RecipeDetailVO;
import com.hart.domain.RecipeVO;

@Service
public interface RecipeService {

	
	public List<RecipeVO> recipelist() throws Exception;
		
	
	
	public RecipeDetailVO recipeDetail(String rid) throws Exception;

}
