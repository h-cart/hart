package com.hart.controller.liveClass;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.liveClass.LiveClassDetailInfoDTO;
import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.LiveClassRegisterDTO;
import com.hart.domain.liveClass.LiveClassVideoDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;
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
	
	@GetMapping("/detail/{lcid}")
	public String getLiveClassListDetail(Model model, @PathVariable String lcid, Principal pr) {
		log.info("getLiveClassListDetail 컨트롤러 호출");
		LiveClassDetailInfoDTO dto = new LiveClassDetailInfoDTO();
		dto = service.getClassDetail(lcid);
		if(pr==null) {
			model.addAttribute("memberCheck",0);
		}else {
			String mid = pr.getName();
			int memberCheck = service.checkClassMember(lcid, mid);
			model.addAttribute("memberCheck",memberCheck);
		}
		
		model.addAttribute("liveClass",dto);
		log.info(model);
		return "liveClass/liveClassDetail";
	}
	
	@GetMapping("/video/{lcid}")
	public String getVideoDetail(@PathVariable String lcid, Model model) {
		//라이브 클래스 정보 담는 부분
		LiveClassDetailInfoDTO dto = new LiveClassDetailInfoDTO();
		dto = service.getClassDetail(lcid);
		model.addAttribute("liveClass",dto);
		
		
		log.info("videoDetail 컨트롤러 호출");
		LiveClassVideoDTO videoInfo =  service.getClassVideo(lcid);
		log.info(videoInfo);
		model.addAttribute("videoInfo",videoInfo);
		return "liveClass/liveClassVideo";
	}
	
	
	
	
	@PostMapping("/register")
	public String registerVOD(Model model, LiveClassRegisterDTO dto) {
		
		log.info(dto);
		try {
			service.registerVOD(dto);
		} catch (Exception e) {
			log.info("vod 등록에 실패했습니다.");
		}
		
		return "redirect:/admin/class";
	}
	
	
//	@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@테스트용
	
	@GetMapping("/mypage")
	public String myPageTest(Principal pr,Model model) {
		log.info("myPage 수강내역 컨트롤러 호출");
		String mid = pr.getName();
		List<MyLiveClassInfoDTO> list =  service.getMyClassInfo(mid);
		log.info(list);
		model.addAttribute("classList",list);
		return "liveClass/liveClassMypage";
	}
	
	
	@GetMapping("/testchat")
	public String streamingTestChat() {
		log.info("streamingTest 컨트롤러 호출");
		
		return "liveClass/liveClassChatTestStream";
	}
	
	@GetMapping("/testdetail/{lcid}")
	public String liveClassDetailTest(Model model, @PathVariable String lcid) {
		LiveClassDetailInfoDTO dto = service.getClassDetail(lcid);
		model.addAttribute("liveClass",dto);
		log.info(model);
		
		return "liveClass/testdetail";
	}
	
	
}
