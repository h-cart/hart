package com.hart.zuik.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.service.share.ShareService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ShareServiceTests {

	@Autowired
	private ShareService sService;
	
	@Test
	public void create ()throws Exception{
		
		log.info(sService.createCart("leesk3732@gmail.com"));
		
	}
}
