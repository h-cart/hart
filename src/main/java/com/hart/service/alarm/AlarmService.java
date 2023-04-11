package com.hart.service.alarm;

import java.sql.SQLException;

public interface AlarmService {
	
	//liveClass 수업 전날 알림을 보내주는 서비스
	public void yesterdayAlarmService()throws SQLException;
	//liveClass 수업 당일날 알림을 등록해주는 서비스 
	public void todayAlarmService() throws SQLException;
	
}
