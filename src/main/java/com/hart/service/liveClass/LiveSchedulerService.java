package com.hart.service.liveClass;

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

@Service
@Log4j2
public class LiveSchedulerService {
	
	@Autowired
	AlarmMapper alarmMapper;

	DefaultMessageService messageService;
	
	public LiveSchedulerService() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSWJKPFH3HYX5YC", "QNFZQZLRSK7MOZDYDPMVAU9ABDANEQ43", "https://api.coolsms.co.kr");
    }
	
	@Scheduled(cron = "0 0 9 * * *")
	public void yesterdayAlarmService() {
		log.info("yesterdayAlarmService 호출");
		Map<String,Object> map = new HashMap<>();
		alarmMapper.getApplicantInfoCall(map);
		List<LiveClassApplicantDTO> list = (List<LiveClassApplicantDTO>)map.get("key");
		log.info(list);
		
		Message message = new Message();
		message.setFrom("01096202055");

		StringBuilder sb = new StringBuilder();
		for(LiveClassApplicantDTO dto : list) {
			sb.append(dto.getLcday()+" "+dto.getLcstart()+" ~ "+dto.getLcend());
			message.setTo(dto.getAlertPhone());
			message.setText("[알람] 내일은 "+dto.getLcname()+" 수업 예정일입니다."+sb.toString()+" 강의 시간에 잊지말고 참석해 주세요!");
			this.messageService.sendOne(new SingleMessageSendingRequest(message));
			sb.setLength(0);
		}
		
		System.out.println("hello Alarm Service");
		
	}
	
	@Scheduled(cron = "2 0 0 * * *")
	public void todayAlarmService() {
		log.info("todayAlarmService 호출");
		alarmMapper.addApplicantTodayAlarm();
	}
	
	/*@@@@@@@@@@@@@@@@@@@@@@@@@테스트용 코드@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
	/*
	@Scheduled(cron = "10 * * * * *")
	public void firstAlarmService() {
		
		log.info("테스트용 10초마다 호출하는 코드");
		Map<String,Object> map = new HashMap<>();
		alarmMapper.getApplicantInfoCall(map);
		List<LiveClassApplicantDTO> list = (List<LiveClassApplicantDTO>)map.get("key");
		log.info(list);
		
		Message message = new Message();
		message.setFrom("01096202055");

		StringBuilder sb = new StringBuilder();
		for(LiveClassApplicantDTO dto : list) {
			sb.append(dto.getLcday()+" "+dto.getLcstart()+" ~ "+dto.getLcend());
			message.setTo(dto.getAlertPhone());
			message.setText("[알람] 내일은 '"+dto.getLcname()+"' 수업 예정일입니다."+sb.toString()+" 강의 시간에 잊지말고 참석해 주세요!");
			this.messageService.sendOne(new SingleMessageSendingRequest(message));
			sb.setLength(0);
		}
		
		System.out.println("hello Alarm Service");
		
	}
	
	@Scheduled(cron = "20 * * * * *")
	public void alarmService() {
		log.info("테스트용 alarmService 20초 마다 호출");
		alarmMapper.addApplicantTodayAlarm();
		
	}
	*/
	
}
