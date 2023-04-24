package com.hart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.alarm.AlarmDTO;
import com.hart.domain.alarm.LiveClassApplicantDTO;
import com.hart.domain.liveClass.LiveClassListDTO;

/**
 * @since : 2023. 3. 15.
 * @FileName: Alarm.java
 * @author : 함세강
 * @설명 : 데이터베이스 mybatis Interface
 * 
 *<pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 4. 01.     함세강           getApplicantInfoCall mapper 구현
 * 2023. 4. 01.     함세강           addApplicantTodayAlarm mapper 구현
 * 2023. 4. 02.     함세강           noticeListCall 구현
 * 2023. 4. 02.     함세강           updateNoticeList 구현
 *</pre>
 */

@Mapper
public interface AlarmMapper {
	//강의 하루전 알람 추가 및 수강생 정보 불러오기
	public void getApplicantInfoCall(Map<String,Object> map);
	//강의 당일날 알림 추가
	public void addApplicantTodayAlarm();
	//헤더 알람 목록 가져오기
	public void noticeListCall(Map<String,Object>map);
	//헤더 알람 목록 최신으로 만들기
	public int updateNoticeList(@Param("mid") String mid);
	
}
