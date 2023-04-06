package com.hart.controller.alarm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.alarm.NoticeDTO;
import com.hart.service.alarm.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
/**
 * @since : 2023. 04. 04.
 * @FileName: LiveClassController.java
 * @author : 함세강
 * @설명 : LiveClass 관련 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 04. 04.     함세강       LiveClassController 구현
 *     </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
@Log4j2
public class AlarmRestController {

	private final NoticeService service;
	
	@PostMapping
	public NoticeDTO getalarm(){
		//Principal pr
		//String mid = pr.getName();
		String mid = "skarns23@yu.ac.kr";
		NoticeDTO dto = service.getNoticeService(mid);
		log.info(dto);
		return dto;
	}
	
	@PostMapping("/update")
	public void alarmStatusUpdate(){
		//Principal pr
		//String mid = pr.getName();
		log.info("alarmStatusUpdate 컨트롤러 호출");
		String mid = "skarns23@yu.ac.kr";
		service.noticeUpdateService(mid);
		
	}
	
}
