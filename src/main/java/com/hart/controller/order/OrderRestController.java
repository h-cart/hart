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

@RestController
@RequestMapping("/oapi")
public class OrderRestController {
	
	@Autowired
	private OrderService oService;
	
	
	@PostMapping(value="/cancel", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,String>> cancle(@RequestBody Map<String,String> map, @AuthenticationPrincipal ClubAuthMemberDTO mDTO){
		String msg ="";
		int status = 200;
		Map<String,String> result = new HashMap<>();
		try {
			System.out.println(map.get("oid"));
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
	
	@PostMapping(value="/search", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,List<SearchResultDTO>>> searchOrder(@AuthenticationPrincipal ClubAuthMemberDTO mDTO, @RequestBody SearchDTO sDTO){
		Map<String,List<SearchResultDTO>> result = new HashMap<String, List<SearchResultDTO>>();
		try {
			sDTO.setMid(mDTO.getMid());
			result.put("result",oService.searchOrders(sDTO));
			return new ResponseEntity<>(result,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
