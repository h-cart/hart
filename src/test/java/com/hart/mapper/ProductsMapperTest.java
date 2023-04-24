package com.hart.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.product.ListVO;
import com.hart.service.ProductsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ProductsMapperTest {

	@Autowired
	private ProductsService productsservice;

	
	@Test
	public void testGetProductsByCategory(ListVO ListVO) {

		int pcno_top = 100571;
		int pcno = 100612;
		String type = "price";
		int sort = 1;
		int start = 0;
		int end = 10;

		
		 //List<ListVO> products = productsservice.List(ListVO);
			/*
			 * assertNotNull(products); assertTrue(products.size() > 0);
			 */

	}

}
