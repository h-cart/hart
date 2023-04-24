package com.hart.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.product.RecipeVO;
import com.hart.mapper.RecipeMapper;

import lombok.extern.log4j.Log4j2;


@Log4j2
@SpringBootTest
public class RecipeMapperTest {
    	
	@Autowired
	private RecipeMapper rMapper;
    
	//recipelist 테스트
	@Test
    void recipelistTest() {
		//List<RecipeVO> recipelist = rMapper.recipelist(set.);
		//System.out.println(recipelist);

		 
    }


	
}