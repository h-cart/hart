package com.hart.controller.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.OrderTotalDTO;
import com.hart.service.order.OrderService;


/**
 * @since : 2023. 3. 20.
 * @FileName: OrderController.java
 * @author : 남승현
 * @설명 : 주문 관련 요청 처리 컨트롤러 
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       OrderController 구현
 *     </pre>
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService oService;
	
	/* *Author : 남승현
	 * 기능 : QR 스캐너 페이지로 이동
	 * 매개변수 : X
	 */
	@GetMapping("/qr")
	public void qr() {

	}

	/* *Author : 남승현
	 * 기능 : 주문 페이지로 이동
	 * 매개변수 : X
	 */
	@GetMapping("/checkout")
	public void showItems() {
		
	}

	/* *Author : 남승현
	 * 기능 : Post Method로 주문 상품관련 데이터를 받아, 페이지로 보내줌
	 * 매개변수 : 상품 아이디, 상품 수량
	 * 기타 : PRG 패턴 적용을 위해, Post 형태로 받아 Redirect 시킴
	 */
	@PostMapping("/list")
	public String list(@RequestParam("pids") List<String> pids, @RequestParam("pamounts") List<Integer> pamounts,RedirectAttributes model) {
		String url = "/order/checkout";
		try {
			OrderTotalDTO oDTO = oService.getInfo(pids, pamounts);
			model.addFlashAttribute("oDTO",oDTO);
		}catch (Exception e) {
			model.addFlashAttribute("msg", "ERROR");
			url = "error";
		}
		return "redirect:"+url;
	}

	/* *Author : 남승현
	 * 기능 : 주문 기능
	 * 매개변수 : OrderInsertDTO(주문정보 / 구매 클래스,상품 목록 / 주소 저장여부)
	 * 기타 : 주문 데이터 삽입 
	 */
	@PostMapping("/complete")
	public String insertOrder(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @ModelAttribute OrderInsertDTO insertDTO) {
		String url = "";
		try {
			insertDTO.getOinfo().setMid(mDTO.getMid());
			Map<String,Object> result = oService.insertOrder(mDTO,insertDTO);
			mDTO = (ClubAuthMemberDTO)result.get("mDTO");
			OinfoDTO oinfo = (OinfoDTO)result.get("oinfo");
			
			url = "/order/complete?oid="+oinfo.getOid();
		}catch (Exception e) {
		
			if(e.getMessage().equals("수강 중인 클래스 존재")) {
				url = "/error";
			}else
				url = "/cart/mycart";
			
		}
		return "redirect:"+url;
	}
	
	/* *Author : 남승현
	 * 기능 : 주문 완료 페이지로 이동
	 * 매개변수 : 주문 번호
	 * 기타 : 주문 프로세스 진행시 PRG 패턴을 통해, 주문 완료 페이지로 이동시킴 
	 */
	@GetMapping("/complete")
	public String orderComplete(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @RequestParam("oid") String oid, Model model) {
		String url = "order/complete";
		try {
			
			model.addAttribute("oinfo", oService.getOrder(mDTO.getMid(), Integer.parseInt(oid)));
			
		}catch (Exception e) {
			model.addAttribute("msg", "오류 발생");
			url = "error";
		}
		
		return url;
		
	}

}
