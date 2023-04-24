package com.hart.service.alarm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hart.domain.alarm.AlarmDTO;
import com.hart.domain.alarm.NoticeDTO;
import com.hart.mapper.AlarmMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 04. 04.
 * @FileName: AlarmService.java
 * @author : 함세강
 * @설명 : NoticeService 구현
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 04. 04.     함세강       getNoticeService 서비스 추가
 * 2023. 04. 05.     함세강       noticeUpdateService 서비스 추가
 *     </pre>
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class NotiveServiceImpl implements NoticeService{
	
	private final AlarmMapper mapper;
	
	@Override
	public NoticeDTO getNoticeService(String mid) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("mid", mid);
		map.put("alarmCount", null);
		mapper.noticeListCall(map);//아이디와 알람수를 키값으로 가지는 map 객체를 매개변수로 사용
		
		NoticeDTO dto = new NoticeDTO();
		dto.setList((List<AlarmDTO>)map.get("key"));
		dto.setAlarmCount((String)map.get("alarmCount"));
		
		return dto;
	}

	@Override
	public void noticeUpdateService(String mid) {
		log.info("noticeUpdateService 호출");
		mapper.updateNoticeList(mid);
	}

}
