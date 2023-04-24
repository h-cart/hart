package com.hart.service.alarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hart.domain.alarm.LiveClassApplicantDTO;
import com.hart.mapper.AlarmMapper;

import lombok.extern.log4j.Log4j2;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

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
@Service
@Log4j2
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	AlarmMapper alarmMapper;
	
	DefaultMessageService messageService;
	
	public AlarmServiceImpl() {
		//계정 내 등록된 유효한 API 키, API Secret Key를 입력
		this.messageService = NurigoApp.INSTANCE.initialize("NCSWJKPFH3HYX5YC", "QNFZQZLRSK7MOZDYDPMVAU9ABDANEQ43",
				"https://api.coolsms.co.kr");
	}
	
	
	@Override
	@Scheduled(cron = "0 0 9 * * *")//오전 9시마다 로직 실행
	public void yesterdayAlarmService() {
		log.info("yesterdayAlarmService 호출");
		Map<String, Object> map = new HashMap<>();
		alarmMapper.getApplicantInfoCall(map);//수강 하루전날 알람을 추가
		List<LiveClassApplicantDTO> list = (List<LiveClassApplicantDTO>) map.get("key");
		log.info(list);

		Message message = new Message();
		message.setFrom("01096202055");

		StringBuilder sb = new StringBuilder();
		for (LiveClassApplicantDTO dto : list) {
			sb.append(dto.getLcday() + " " + dto.getLcstart() + " ~ " + dto.getLcend());
			message.setTo(dto.getAlertPhone());
			message.setText("[알람] 내일은 " + dto.getLcname() + " 수업 예정일입니다." + sb.toString() + " 강의 시간에 잊지말고 참석해 주세요!");
			this.messageService.sendOne(new SingleMessageSendingRequest(message));//SMS 알림을 발신하는부분
			sb.setLength(0);
		}
		
	}

	@Override
	@Scheduled(cron = "2 0 0 * * *")// 자정 2초에 로직 실행
	public void todayAlarmService() {
		log.info("todayAlarmService 호출");
		alarmMapper.addApplicantTodayAlarm();//수업 당일날 알람을 추가
	}

}
