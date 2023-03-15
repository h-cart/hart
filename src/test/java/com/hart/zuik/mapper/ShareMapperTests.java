package com.hart.zuik.mapper;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}