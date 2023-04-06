package com.hart.service.alarm;

import com.hart.domain.alarm.NoticeDTO;

public interface NoticeService {
	
	//헤더 부분 알람을 불러와 주는 서비스
	public NoticeDTO getNoticeService(String mid);
	//헤더 부분 알람을 클릭하면 알람상태 업데이트 서비스
	public void noticeUpdateService(String mid);
	
	
}
