package com.hart.ham.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.alarm.LiveClassApplicantDTO;
import com.hart.mapper.AlarmMapper;
import com.hart.mapper.LiveClassMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AdminMapperTest {
	
	@Autowired
	private LiveClassMapper mapper;
	
	@Test
	public void testAdminLiveClass() {
		log.info(mapper.getAdminLiveClass());
		
	}


	
}
