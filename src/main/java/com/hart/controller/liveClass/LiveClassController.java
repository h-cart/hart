package com.hart.controller.liveClass;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.service.liveClass.LiveClassService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 3. 15.
 * @FileName: LiveClassController.java
 * @author : 함세강
 * @설명 : LiveClass 관련 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강       LiveClassController 구현
 *     </pre>
 */
@Controller
@RequestMapping("/class")
@RequiredArgsConstructor
@Log4j2
public class LiveClassController {

	private final LiveClassService service;
	
	@GetMapping
	public String getLiveClassList(Model model) {
		log.info("getLiveClassList 컨트롤러 호출");
		List<LiveClassListDTO> list = service.getList();
		model.addAttribute("liveClassList",list);
		return "liveClass/liveClassList";
	}
	
	@GetMapping("/{lcid}")
	public String getLiveClassListDetail(Model model, @PathVariable String lcid) {
		log.info("getLiveClassListDetail 컨트롤러 호출");
		LiveClassListDTO dto = service.getClassDetail(lcid);
		model.addAttribute("liveClass",dto);
		return "liveClass/liveClassDetail";
	}
	
	@GetMapping("/video/{lcid}")
	public String getVideoDetail(@PathVariable String lcid) {
		log.info("videoDetail 컨트롤러 호출");
		
		
		
		return "liveClass/liveClassVideo";
	}
	
	
//	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@테스트용
	@GetMapping("/testchat")
	public String streamingTestChat() {
		log.info("streamingTest 컨트롤러 호출");
		
		return "liveClass/liveClassChatTestStream";
	}
	
	@GetMapping("/mypage")
	public String myPageTest() {
		log.info("myPageTest 컨트롤러 호출");
		
		return "liveClass/test";
	}
	
}
