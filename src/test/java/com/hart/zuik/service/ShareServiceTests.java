package com.hart.zuik.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;
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
	
	@Test
	public void getInfoWithKey ()throws Exception{
		
		log.info(sService.getInfoWithKey(ShareDTO.builder()
											.cskey("0owuam76ka7kob8VFhqM")
											.csno(24).build()));
		
	}
	
	@Test
	public void update() throws Exception{
		log.info(sService.update(CartInsertDTO.builder().pid("S02006004332").pamount(2).build(), 23));
	}
}
