package com.hart.domain.product;

import java.util.List;

import lombok.Data;
@Data
public class RecipeDetailVO {


	private List<RecipeContentVO> Rcontent;
	private RecipeVO recipedetailtop;

	
	public RecipeDetailVO(RecipeVO recipedetailtop, List<RecipeContentVO> Rcontent) {
		this.recipedetailtop = recipedetailtop;
		this.Rcontent = Rcontent;
	}
	
};
