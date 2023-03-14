package com.hart.controller.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.cart.CartDTO;
import com.hart.service.cart.CartService;

@RestController
@RequestMapping("/capi")
public class CartRestController {
	
	@Autowired
	private CartService cService;
	
	
	@PostMapping(value="/insert", consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,String>> insertCart(@RequestBody Map<String,List<String>> map){
		String mid = "skarns23";
		Map<String,String> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			List<String> pamounts = map.get("pamounts");
			cService.CartInsert(pids, pamounts, mid);
			result.put("result", "success");
			return new ResponseEntity<Map<String,String>>(result,HttpStatus.OK);
			
		}catch (Exception e) {
			result.put("result", e.getMessage());
			return new ResponseEntity<Map<String,String>>(result,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/get",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,CartDTO>> getCarts(){
		String mid = "skarns23";
		Map<String,CartDTO> map = new HashMap<>();
		try {
			map.put("result", cService.getCarts(mid));
			return new ResponseEntity<Map<String,CartDTO>>(map,HttpStatus.OK);
		}catch (Exception e) {
			map.put("result",null);
			return new ResponseEntity<Map<String,CartDTO>>(map,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
