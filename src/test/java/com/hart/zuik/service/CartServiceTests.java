package com.hart.zuik.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
			pids.add("S02006004332");
			pids.add("S02302148600");
			List<String> pamounts = new ArrayList<>();
			pamounts.add("2");
			pamounts.add("3");
			String mid = "skarns23";
			log.info(cService.CartInsert(pids,pamounts, mid));
		}catch (Exception e) {
			log.info(e.getMessage());
		}
		}
}
