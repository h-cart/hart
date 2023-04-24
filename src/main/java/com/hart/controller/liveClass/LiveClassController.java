package com.hart.controller.liveClass;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * 2023. 3. 16.     함세강       getLiveClassList 구현
 * 2023. 3. 20.     함세강       getLiveClassListDetail 구현
 * 2023. 3. 21.     함세강       getVideoDetail 구현
 * 2023. 3. 25.     함세강       registerVOD 구현
 *     </pre>
 */
@Controller
@RequestMapping("/class")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://localhost:80")
@Log4j2
public class LiveClassController {

	private final LiveClassService service;
	
	@GetMapping
	public String getLiveClassList(Model model) {
		log.info("getLiveClassList 컨트롤러 호출");
		//라이브 클래스 목록 불러오는 서비스 호출
		List<LiveClassListDTO> list;
		String msg = "";
		int status =0;
		try {
			list = service.getList();
			model.addAttribute("liveClassList",list);
			status =1;
		} catch (Exception e) {
			msg = "라이브 클래스 목록을 불러올 수 없습니다.";
			model.addAttribute("message",msg);
			log.info(e.getMessage());
			status = -1;
		}
		model.addAttribute("status", status);
		return "liveClass/liveClassList";
	}
	
	@GetMapping("/detail/{lcid}")
	public String getLiveClassListDetail(Model model, @PathVariable String lcid, Principal pr) {
		log.info("getLiveClassListDetail 컨트롤러 호출");
		LiveClassDetailInfoDTO dto = new LiveClassDetailInfoDTO();
		try {
			dto = service.getClassDetail(lcid);
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		if(pr==null) {
			model.addAttribute("memberCheck",0);
		}else {
			String mid = pr.getName();
			int memberCheck;
			try {//이미 구매한 강의인지 체크해 주는 과정
				memberCheck = service.checkClassMember(lcid, mid);
				model.addAttribute("memberCheck",memberCheck);
			} catch (SQLException e) {
				log.info(e.getMessage());
			}
		}
		model.addAttribute("liveClass",dto);
		return "liveClass/liveClassDetail";
	}
	
	@GetMapping("/video/{lcid}")
	@CrossOrigin(origins = "*", methods = RequestMethod.GET)
	public String getVideoDetail(@PathVariable String lcid, Model model) {
		//라이브 클래스 정보 담는 과정
		LiveClassDetailInfoDTO dto = new LiveClassDetailInfoDTO();
		try {
			dto = service.getClassDetail(lcid);
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		model.addAttribute("liveClass",dto);
		log.info("videoDetail 컨트롤러 호출");
		LiveClassVideoDTO videoInfo;
		try {
			videoInfo = service.getClassVideo(lcid);
			model.addAttribute("videoInfo",videoInfo);
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return "liveClass/liveClassVideo";
	}
	

	@PostMapping("/register")
	public String registerVOD(Model model, LiveClassRegisterDTO dto) {
		try {
			//라이브 클래스 관리자 부분에서 VOD를 등록하는 서비스
			service.registerVOD(dto);
		} catch (Exception e) {
			log.info("vod 등록에 실패했습니다.");
		}
		
		return "redirect:/admin/class";
	}
	
	
}
