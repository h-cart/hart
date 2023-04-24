package com.hart.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;
import com.hart.mapper.ShareMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ShareMapperTests {
	
	@Autowired
	private ShareMapper sMapper;
	
	
	@Test
	public void create() throws Exception{
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 20;
		Random random = new Random();

		String generatedString = random.ints(leftLimit,rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(targetStringLength)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		log.info(sMapper.create(ShareDTO.builder()
								.cskey(generatedString)
								.mid("skarns23@gmail.com")
								.build()));
	}


	@Test
	public void getInfo() throws Exception{
		ShareDTO sDTO = sMapper.getInfo("skarns23@gmail.com");
		log.info(sDTO);
		}
	@Test
	public void getInfoWithKey() throws Exception{
		ShareDTO sDTO = sMapper.getInfoWithKey(ShareDTO.builder()
												.csno(24)
												.cskey("0owuam76ka7kob8VFhqM")
												.build());
		log.info(sDTO);
	}
	
	@Test
	public void update() throws Exception{
		sMapper.updateAmount(CartInsertDTO.builder().pid("S02006004332").pamount(3).build(), 23);
	}
	
	@Test
	public void getProducts() throws Exception{
		log.info(sMapper.getProducts("24"));
	}
	
	@Test
	public void insertProducts() throws Exception{
		log.info(sMapper.insertCarts(CartInsertDTO.builder()
											.pid("S02211132600")
											.pamount(1).build(), 24));	
	}

	@Test
	public void removesCarts() throws Exception {
		List<String> pids = new ArrayList<>();
		pids.add("S02006004332");
		pids.add("S02211132600");
		log.info(sMapper.removeCart(pids, 24));
	}
}