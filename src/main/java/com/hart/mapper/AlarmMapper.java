package com.hart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.alarm.AlarmDTO;
import com.hart.domain.alarm.LiveClassApplicantDTO;
import com.hart.domain.liveClass.LiveClassListDTO;

/**
 * @since : 2023. 3. 15.
 * @FileName: Alarm.java
 * @author : 함세강
 * @설명 : 데이터베이스 mybatis Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강           LiveClassMapper
 *     </pre>
 */

@Mapper
public interface AlarmMapper {
	//강의 하루전 알람 추가 및 수강생 정보 불러오기
	public void getApplicantInfoCall(Map<String,Object> map);
	//강의 당일날 알림 추가
	public void addApplicantTodayAlarm();
	//헤더 알람 목록 가져오기
	public List<AlarmDTO> getAlarmList(String mid);
	
}
