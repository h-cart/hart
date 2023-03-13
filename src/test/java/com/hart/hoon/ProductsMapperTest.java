package com.hart.hoon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.ProductsVO;
import com.hart.service.ProductsService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class ProductsMapperTest {

	@Autowired
	private ProductsService productsservice;

	
	@Test
	public void ProductTests() throws Exception{
		
		
		ProductsVO productsVO =null;
		List<ProductsVO> getProductList = productsservice.getProductList(productsVO);
		log.info("getProductList! :" +getProductList);
	}
}
