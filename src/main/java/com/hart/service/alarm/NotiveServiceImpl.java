package com.hart.service.alarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hart.domain.alarm.AlarmDTO;
import com.hart.domain.alarm.NoticeDTO;
import com.hart.mapper.AlarmMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotiveServiceImpl implements NoticeService{
	
	private final AlarmMapper mapper;
	
	@Override
	public NoticeDTO getNoticeService(String mid) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("mid", mid);
		map.put("alarmCount", null);
		mapper.noticeListCall(map);
		
		NoticeDTO dto = new NoticeDTO();
		dto.setList((List<AlarmDTO>)map.get("key"));
		dto.setAlarmCount((String)map.get("alarmCount"));
		
		return dto;
	}

	@Override
	public void noticeUpdateService(String mid) {
		log.info("noticeUpdateService 호출");
		mapper.updateNoticeList(mid);
		log.info("noticeUpdateService 호출완료");
	}

}
