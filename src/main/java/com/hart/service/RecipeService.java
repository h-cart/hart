package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.product.RecipeDetailVO;
import com.hart.domain.product.RecipeVO;


/**
 * @since : 2023. 03.28.
 * @FileName: ProductsService.java
 * @author : 박정훈
 * @설명 : 레시피 리스트 및 상세 서비스
 * 
 */
@Service
public interface RecipeService {
	
	public List<RecipeVO> recipelist(RecipeVO recipelist) throws Exception;
		
	public RecipeDetailVO recipeDetail(String rid) throws Exception;

	public List<RecipeVO> mainrecipe();

}
