package com.hart.controller.recommand;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.service.recommand.RecommandService;

@RestController
@RequestMapping("/recommand")
public class RecommandRestController {
	@Autowired
	private RecommandService rService;
	
	@GetMapping(value="/list", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,RecommandDTO>> getRecommandList(@AuthenticationPrincipal ClubAuthMemberDTO mDTO){
		Map<String,RecommandDTO> result = new HashMap<>();
		try {
			result.put("result",rService.getRecommand(mDTO.getMid(),mDTO.getCsno()));
			return new ResponseEntity<>(result,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value="/products/{pid}/recommendations",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String,RecommandDTO>> getRecommands(@PathVariable("pid")String pid){
		
		
		return null;
	}
	
	
}
