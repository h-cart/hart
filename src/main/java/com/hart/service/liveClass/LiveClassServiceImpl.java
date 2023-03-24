package com.hart.service.liveClass;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;
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
		log.info(list);
		return list;
	}

	@Override
	public LiveClassListDTO getClassDetail(String lcid) {
		log.info("getClassDetail 서비스 호출");
		LiveClassListDTO dto = mapper.getLiveClassDetail(lcid);
		StringBuilder sb = new StringBuilder();
		sb.append(dto.getLcdate()+"("+dto.getLcday()+") "+dto.getLcstart()+"-"+dto.getLcend());
		dto.setWholeDate(sb.toString());
		log.info(dto);
		return dto;
	}

	@Override
	public List<MyLiveClassInfoDTO> getMyClassInfo(String mid) {
		List<MyLiveClassInfoDTO> list = mapper.getMyLiveClassInfo(mid);
		Calendar startTime = Calendar.getInstance(); 
		Calendar endTime = Calendar.getInstance(); 
		long now = System.currentTimeMillis();
		
		for(MyLiveClassInfoDTO dto : list) {
			startTime.set(Calendar.YEAR, Integer.parseInt(dto.getLcday().substring(0, 4)));
			startTime.set(Calendar.MONTH, Integer.parseInt(dto.getLcday().substring(5, 7))-1);
			startTime.set(Calendar.DATE, Integer.parseInt(dto.getLcday().substring(8,10)));
			startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dto.getLcstart().substring(0, 2)));
			startTime.set(Calendar.MINUTE, Integer.parseInt(dto.getLcstart().substring(3)));
			startTime.set(Calendar.SECOND, 0);
			
			endTime.set(Calendar.YEAR, Integer.parseInt(dto.getLcday().substring(0, 4)));
			endTime.set(Calendar.MONTH, Integer.parseInt(dto.getLcday().substring(5, 7))-1);
			endTime.set(Calendar.DATE, Integer.parseInt(dto.getLcday().substring(8,10)));
			endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dto.getLcend().substring(0, 2)));
			endTime.set(Calendar.MINUTE, Integer.parseInt(dto.getLcend().substring(3)));
			endTime.set(Calendar.SECOND, 0);
			
			long startDateMillis = startTime.getTimeInMillis();
			long endDateMillis = endTime.getTimeInMillis();
			if(startDateMillis<=now && now<=endDateMillis) {
				dto.setLcstatus(1);//방송 중
			} else if(endDateMillis<now) {
				dto.setLcstatus(2);//지난 방송
			}
			
		}
		log.info(list);
		
		
		return list;
		
	}

	

}
