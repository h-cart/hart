package com.hart.controller.liveClass;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;
import com.hart.domain.member.ClubAuthMemberDTO;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Log4j2
public class LiveClassRestController {

	private final LiveClassService service;
	
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/class")
	public List<MyLiveClassInfoDTO> myLiveClassInfo(Principal pr,@RequestParam("classStatus") int classStatus) {
		String id = pr.getName();
		log.info("myLiveClassInfo rest 컨트롤러 호출");
		log.info(classStatus);
		
		//List<MyLiveClassInfoDTO> list = service.getMyClassInfo(id);
		
		List<MyLiveClassInfoDTO> list = new ArrayList<MyLiveClassInfoDTO>();
		
		return list;
		
	}
	
	
	
}
