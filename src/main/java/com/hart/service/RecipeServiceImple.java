package com.hart.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.RecipeContentVO;
import com.hart.domain.RecipeDetailVO;
import com.hart.domain.RecipeVO;
import com.hart.mapper.RecipeMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RecipeServiceImple implements RecipeService{

	@Autowired
	RecipeMapper recipemapper;
	
	@Override
	public List<RecipeVO> recipelist() {
		
		System.out.println("recipe==============");
		return recipemapper.recipelist();
	}
	
	
	@Override
	public RecipeDetailVO recipeDetail(String rid) {
		
			
			RecipeVO recipedetailtop = recipemapper.recipeDetail(rid);
			List<RecipeContentVO> Rcontent = recipemapper.RecipeContent(rid);
			

			return new RecipeDetailVO(recipedetailtop, Rcontent);
	}
	
	
	
}
