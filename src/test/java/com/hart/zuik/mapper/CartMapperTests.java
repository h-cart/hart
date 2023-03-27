package com.hart.zuik.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.cart.CartInsertDTO;
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
								.pid("L0001")
								.pamount(1)
								.build();
		
		int result = cMapper.insertCarts(cDTO, "skarns23@gmail.com");
		log.info("result ======================================");
		log.info(result);
	}
	
	@Test
	public void isExistProduct() throws Exception{
		log.info(cMapper.isExistProduct(""
				+ ""));
	}
	
	@Test
	public void getProducts() throws Exception{
		cMapper.getProducts("skarns23").forEach(item -> log.info(item));
	}
	
	@Test
	public void updateProducts() throws Exception{
		log.info(cMapper.updateAmount(CartInsertDTO.builder().pid("S02211132600")
										.pamount(2).build(), "skarns23@gmail.com"));
	}

	@Test
	public void removesCarts() throws Exception {
		List<String> pids = new ArrayList<>();
		pids.add("S02211132600");
		pids.add("L01");
		log.info(cMapper.removeCart(pids, "skarns23@gmail.com"));
	}
	
	@Test
	public void getClasss() throws Exception {
		log.info(cMapper.getLClass("skarns23@gmail.com"));
	}
	
}
