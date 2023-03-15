package com.hart.service.liveClass;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.mapper.LiveClassMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LiveClassServiceImpl implements LiveClassService{

	private final LiveClassMapper mapper;
	
	@Override
	public List<LiveClassListDTO> getList() {
		
		log.info("getList 서비스 호출");
		
		List<LiveClassListDTO> list = mapper.getLiveList();
		return list;
	}

}
