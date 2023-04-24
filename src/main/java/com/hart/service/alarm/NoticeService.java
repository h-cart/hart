package com.hart.service.alarm;

import com.hart.domain.alarm.NoticeDTO;

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
public interface NoticeService {
	
	//헤더 부분 알람을 불러와 주는 서비스
	public NoticeDTO getNoticeService(String mid);
	//헤더 부분 알람을 클릭하면 알람상태 업데이트 서비스
	public void noticeUpdateService(String mid);
	
	
}
