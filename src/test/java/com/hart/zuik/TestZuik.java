package com.hart.zuik;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.mapper.TestMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TestZuik {
	
	@Autowired
	TestMapper mapper;
	
	
	@Test
	public void Test() {
		log.info(mapper.getTest());
	}

}
