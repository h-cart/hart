package com.hart.service.alarm;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.alarm.AlarmDTO;
import com.hart.mapper.AlarmMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotiveServiceImpl implements NoticeService{
	
	private final AlarmMapper mapper;
	
	@Override
	public List<AlarmDTO> getNoticeService(String mid) {
		
		List<AlarmDTO> list = mapper.getAlarmList(mid);
		
		return list;
	}

}
