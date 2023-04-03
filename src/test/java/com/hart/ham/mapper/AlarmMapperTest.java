package com.hart.ham.mapper;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.alarm.LiveClassApplicantDTO;
import com.hart.mapper.AlarmMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AlarmMapperTest {
	
	@Autowired
	private AlarmMapper mapper;
	
	/*
	@Transactional
	@Test
	public void getApplicantInfo() {
		log.info(mapper.getApplicantInfo());
		try {
			Map<String, Object> resultMap = mapper.getApplicantInfo();
			
			ResultSet resultSet = (ResultSet)resultMap.get("cursor");
			
			List<LiveClassApplicantDTO> resultList = new ArrayList<>();
		
		
			while (resultSet.next()) {
				LiveClassApplicantDTO dto = new LiveClassApplicantDTO();
				dto.setMid(resultSet.getString("MID"));
				resultList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	
	@Test
	public void test() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		mapper.getApplicantInfoCall(map);
		List<LiveClassApplicantDTO> list = (List<LiveClassApplicantDTO>)map.get("key");
		log.info(list);
	}

	@Test
	public void test2() {
		mapper.addApplicantTodayAlarm();
		
	}
	
}
