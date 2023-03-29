package com.hart.ham.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.liveClass.LiveClassDetailInfoDTO;
import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;
import com.hart.service.liveClass.LiveClassService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LiveClassServiceTest {
	
	@Autowired
	private LiveClassService service;
	
	@Test
	public void getListServiceTest() {
		List<LiveClassListDTO> list = service.getList();
		log.info(list);
		assertThat(list.get(0)).isInstanceOf(LiveClassListDTO.class);
	}
	
	@Test
	public void getMyClassInfoServiceTest() {
		String mid = "skarns23@gmail.com";
		List<MyLiveClassInfoDTO> list = service.getMyClassInfo(mid);
		
		log.info(list);
		//assertThat(list.get(0)).isInstanceOf(MyLiveClassInfoDTO.class);
		assertThat(list.get(0)).isInstanceOf(MyLiveClassInfoDTO.class);
	}
	
	@Test
	public void getLiveClassDetailSetvideTest() {
		LiveClassDetailInfoDTO dto = service.getClassDetail("L0003");
		log.info(dto);
		assertThat(dto).isInstanceOf(LiveClassDetailInfoDTO.class);
		
	}
	
	
}
