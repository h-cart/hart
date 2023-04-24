package com.hart.service.alarm;

import java.sql.SQLException;

/**
 * @since : 2023. 04. 04.
 * @FileName: AlarmService.java
 * @author : 함세강
 * @설명 : AlarmService 구현
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 04. 04.     함세강       yesterdayAlarmService 서비스 추가
 * 2023. 04. 05.     함세강       todayAlarmService 서비스 추가
 *     </pre>
 */
public interface AlarmService {
	
	//liveClass 수업 전날 알림을 보내주는 서비스
	public void yesterdayAlarmService()throws SQLException;
	//liveClass 수업 당일날 알림을 등록해주는 서비스 
	public void todayAlarmService() throws SQLException;
	
}
