package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.product.RecipeContentVO;
import com.hart.domain.product.RecipeVO;



/**
 * @since : 2023. 03.28.
 * @FileName: RecipeMapper.java
 * @author : 박정훈
 * @설명 : 데이터베이스 마이바티스 인터페이스
 * 
 */
@Mapper
public interface RecipeMapper {
	
	//레시피 리스트 가져오기
	public List<RecipeVO> recipelist(RecipeVO recipelist);
	//레시피 상세 정보 가져오기
	RecipeVO recipeDetail(String rid);
	//레시피 컨텐츠 가져오기
	List<RecipeContentVO> RecipeContent(String rid);
	//레시피 메인페이지 레시피 추천 가져오기
	public List<RecipeVO> mainrecipe();
}
