package com.hart.controller.mypage;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.liveClass.MyLiveClassInfoDTO;
import com.hart.service.liveClass.LiveClassService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 3. 15.
 * @FileName: LiveClassController.java
 * @author : 함세강, 남승현
 * @설명 : 마이페이지
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강       myPageClass 핸들러 추가
 *     </pre>
 */
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class MypageController {
private final LiveClassService service;
	
	
	@GetMapping("/class")
	public String myPageClass(Principal pr,Model model) {
		log.info("myPage 수강내역 확인 컨트롤러 호출");
		String mid = pr.getName();
		log.info(mid);
		List<MyLiveClassInfoDTO> list =  service.getMyClassInfo(mid);
		log.info(list);
		model.addAttribute("classList",list);
		return "liveClass/liveClassMypage";
	}
	
	@GetMapping("/order")
	public String getOrderList() {
		return "member/orderlist";
	}
	
	
//	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@테스트용
	@GetMapping("/testchat")
	public String streamingTestChat() {
		log.info("streamingTest 컨트롤러 호출");
		
		return "liveClass/liveClassChatTestStream";
	}
}
