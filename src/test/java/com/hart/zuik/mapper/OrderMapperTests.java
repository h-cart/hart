package com.hart.zuik.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hart.mapper.OrderMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class OrderMapperTests {

	@Autowired
	private OrderMapper oMapper;

	@Test
	public void getProducts() throws Exception{
		List<String> list = new ArrayList<>();
		log.info(oMapper);
		list.add("S02209125009");
		list.add("S02105049300");
		list.add("S02006004340");
		list.add("S02006004339");
		
		
				
	}
	
}
