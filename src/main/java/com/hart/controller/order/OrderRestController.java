package com.hart.controller.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.order.SearchDTO;
import com.hart.domain.order.SearchResultDTO;
import com.hart.service.order.OrderService;

/**
 * @since : 2023. 3. 20.
 * @FileName: OrderRestController.java
 * @author : 남승현
 * @설명 : 주문 내역 조회시, 활용되는 RestController
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       OrderRestController 구현
 *     </pre>
 */
@RestController
@RequestMapping("/oapi")
public class OrderRestController {
	
	@Autowired
	private OrderService oService;
	
	
	/* *Author : 남승현
	 * 기능 : 주문 취소
	 * 매개변수 : 주문 번호
	 * 기타 : 주문 취소가 가능한 상태인 주문에 대해, 주문 취소를 진행 후, 사용자 마일리지 및 상품 판매량 갱신
	 */
	@PostMapping(value="/cancel", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,String>> cancle(@RequestBody Map<String,String> map, @AuthenticationPrincipal ClubAuthMemberDTO mDTO){
		String msg ="";
		int status = 200;
		Map<String,String> result = new HashMap<>();
		try {
			int oid = Integer.parseInt(map.get("oid"));
			int mpoint = oService.orderCancle(mDTO.getMid(), oid,mDTO.getSocial());
			mDTO.setMpoint(mpoint);
			msg = "success";
		}catch (Exception e) {
			msg = "fail";
			status = 400;
		}
		result.put("result",msg);
		return new ResponseEntity<Map<String,String>>(result,status==200?HttpStatus.OK:HttpStatus.BAD_REQUEST);
	}
	
	/* *Author : 남승현
	 * 기능 : 주문 조회
	 * 매개변수 : SearchDTO(사용자 아이디, 조회 시작일, 조회 종료일, 취소 여부)
	 * 기타 : 조회 시작일 - 조회 종료일 내에 사용자를 통해 주문 / 주문 취소 된 내역 조회
	 */
	@PostMapping(value="/search", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,List<SearchResultDTO>>> searchOrder(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @RequestBody SearchDTO sDTO){
		Map<String,List<SearchResultDTO>> result = new HashMap<String, List<SearchResultDTO>>();
		try {
			sDTO.setMid(mDTO.getMid());
			result.put("result",oService.searchOrders(sDTO));
			return new ResponseEntity<>(result,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
