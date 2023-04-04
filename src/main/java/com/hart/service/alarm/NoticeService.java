package com.hart.service.alarm;

import java.util.List;

import com.hart.domain.alarm.AlarmDTO;

public interface NoticeService {
	
	//헤더 부분 알람을 불러와 주는 서비스
	public List<AlarmDTO> getNoticeService(String mid);
	
}
