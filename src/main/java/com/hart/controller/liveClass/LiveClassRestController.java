package com.hart.controller.liveClass;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/liveClass")
@RequiredArgsConstructor
@Log4j2
public class LiveClassRestController {

	private final LiveClassService service;
	
	@GetMapping("/info")
	public LiveClassListDTO myLiveClassInfo(Principal pr) {
		LiveClassListDTO dto = new LiveClassListDTO();
		String id = pr.getName();
		
		return dto;
		
	}
	
}
