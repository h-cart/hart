package com.hart.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.LiveClassRegisterDTO;
import com.hart.domain.liveClass.LiveClassVideoDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;
import com.hart.mapper.LiveClassMapper;
import com.hart.service.liveClass.LiveClassService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LiveClassMapperTest {
	
	@Autowired
	private LiveClassMapper mapper;
	
	@Test
	public void getListService() throws SQLException {
		log.info(mapper.getLiveList());
	}
	
	@Test
	public void getVideoInfo() throws SQLException {
		log.info(mapper.getMyVideo("L0001"));
		assertThat(mapper.getMyVideo("L0001")).isInstanceOf(LiveClassVideoDTO.class);
	}
	
	
	@Test
	public void registerVOD() throws SQLException {
		LiveClassRegisterDTO dto = new LiveClassRegisterDTO();
		dto.setClassNumber("L0078");
		dto.setClassVodpath("https://liveclasspath");
		log.info(mapper.registerLiveClassVOD(dto));
		
	}
	
	@Test
	public void checkClassMemberTest() throws SQLException{
		log.info(mapper.checkClassMember("L0001","gkatprkd@gmail.com"));
		assertThat(mapper.checkClassMember("L0001","gkatprkd@gmail.com")).isNotEqualTo(0);
	}
	
}
