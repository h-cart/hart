package com.hart.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.RecipeVO;

@Mapper
public interface RecipeMapper {

	public List<RecipeVO> recipelist();
	
	public RecipeVO recipeDetail(String rid);
	
	
}
