package com.hart.zuik.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hart.service.order.OrderService;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class OrderServiceTest {
	
	@Autowired
	private OrderService oService;
	
	@Test
	public void getP() {
		List<String> list = new ArrayList<>();
		list.add("S02209125009");
		list.add("S02105049300");
		list.add("S02006004340");
		list.add("S02006004339");
		
	}
}
