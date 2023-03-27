package com.hart.hoon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hart.domain.product.RecipeVO;
import com.hart.mapper.RecipeMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class RecipeMapperTest {
    	
	@Autowired
	private RecipeMapper rMapper;
    
	
	@Test
    void testRecipeDetail() {
		
		  //log.info(rMapper.recipeDetail(210105100006001L));
		 
    }
	@Test
    void test2() {
		
		log.info(rMapper.recipelist());
        
	
	
    }
}