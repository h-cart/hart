package com.hart.zuik.mapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.CartInsertDTO;
import com.hart.mapper.CartMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CartMapperTests {
	
	@Autowired
	private CartMapper cMapper;
	
	@Test
	public void insertTest() throws Exception {
		CartInsertDTO cDTO = CartInsertDTO.builder()
								.pid("s123")
								.pamount(3)
								.build();
		
		int result = cMapper.insertCarts(cDTO, "skarns23");
		log.info("result ======================================");
		log.info(result);
	}
	
	@Test
	public void isExistProduct() throws Exception{
		log.info(cMapper.isExistProduct(""
				+ ""));
	}
}
