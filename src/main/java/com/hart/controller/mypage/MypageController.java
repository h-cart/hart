package com.hart.controller.mypage;

import java.security.Principal;
import java.sql.SQLException;
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
 * 2023. 3. 17.     함세강       getOrderList 핸들러 추가
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
		log.info("myPage 수강내역 컨트롤러");
		String mid = pr.getName();
		log.info(mid);
		List<MyLiveClassInfoDTO> list;
		try {
			list = service.getMyClassInfo(mid);
			log.info(list);
			model.addAttribute("classList",list);
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return "liveClass/liveClassMypage";
	}
	
	/* *Author : 남승현
	 * 기능 : 마이페이지내 주문내역 페이지로 이동
	 * 매개변수 : X
	 * 기타 : 기본 값으로 주문내역 조회 / 페이지내 선택을 통해 주문취소 내역 조회 가능
	 */
	@GetMapping("/order")
	public String getOrderList() {
		log.info("myPage 주문내역 컨트롤러");
		return "member/orderlist";
	}
	
	
}
