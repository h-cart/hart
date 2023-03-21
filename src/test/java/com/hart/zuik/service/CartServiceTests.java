package com.hart.zuik.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.service.cart.CartService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CartServiceTests {

	@Autowired
	private CartService cService;

	@Test
	public void inserts() {

		try {
			List<String> pids = new ArrayList<>();
			pids.add("S02209125009");
			pids.add("S02302148600");
			List<String> pamounts = new ArrayList<>();
			pamounts.add("2");
			pamounts.add("3");
			String mid = "skarns23@gmail.com";
			log.info(cService.CartInsert(pids, pamounts, mid));
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void getCarts() throws Exception{
		try {
			CartDTO cDTO = cService.getCarts("skarns23@gmail.com");
			cDTO.getPLists().forEach(item -> log.info(item));
			cDTO.getCLists().forEach(item -> log.info(item));
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	@Test
	public void updateAmount() throws Exception{
		try {
			int result = cService.updateAmount(CartInsertDTO.builder()
											.pid("S02209125009")
											.pamount(99).build(), "skarns23@gmail.com");
			log.info(result);
											
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Test
	public void deleteProducts() throws Exception{
		try {
			List<String> pids = new ArrayList<>();
			pids.add("S02209125009");
			pids.add("S02105049300");
			log.info(cService.deleteProducts(pids, "skarns23@gmail.com"));
			
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
}
