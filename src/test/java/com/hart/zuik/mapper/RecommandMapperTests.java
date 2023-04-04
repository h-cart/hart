package com.hart.zuik.mapper;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.recommand.RecommandDTO;
import com.hart.mapper.RecommandMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class RecommandMapperTests {
	
	@Autowired
	private RecommandMapper rMapper;
	
	
	@Test
	public void getRecommands() throws SQLException{
		RecommandDTO result = rMapper.getMyRecommand("skarns23@gmail.com");
		log.info(result);
	}
	
	@Test
	public void getShareRecommand() throws SQLException{
		RecommandDTO result = rMapper.getShareRecommand("77");
		log.info(result);
	}
}
