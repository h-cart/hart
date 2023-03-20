package com.hart.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.RecipeVO;
import com.hart.mapper.RecipeMapper;


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
	public RecipeVO recipeDetail(String rid) {
		
		System.out.println("recipeDetailServiceImple=========" + rid);
		
		System.out.println("recipeService 임플 rid>>" +recipemapper.recipeDetail(rid));
		
		return recipemapper.recipeDetail(rid);
	}
	
}
