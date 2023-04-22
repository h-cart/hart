package com.hart.controller.recommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.recommand.IngredientDTO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.domain.share.SseEmitters;
import com.hart.service.cart.CartService;
import com.hart.service.recommand.RecommandService;
import com.hart.service.share.ShareService;

/**
 * @since : 2023. 3. 24.
 * @FileName: RecommandRestController.java
 * @author : 남승현
 * @설명 : 추천 기능 처리 관련 RestController
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 24.     남승현       RecommandRestController 구현
 *     </pre>
 */
@RestController
@RequestMapping("/recommand")
public class RecommandRestController {
	@Autowired
	private RecommandService rService;
	
	private final SseEmitters sseEmitters;

	public RecommandRestController(SseEmitters sseEmitters) {
		this.sseEmitters = sseEmitters;
	}

	@Autowired
	private CartService cService;

	@Autowired
	private ShareService sService;

	/* *Author : 남승현
	 * 기능 : 장바구니 최근 담은 상품 기반, 추천 클래시 / 레시피를 불러옴
	 * 매개변수 : X
	 * 기타 : 장바구니에 가장 최근에 담긴 상품을 기반으로 추천 레시피 / 클래스를 불러옴 
	 * 		 클래스 혹은 레시피를 추가하는 경우, JS를 통해 처리
	 */
	@GetMapping(value = "/list", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, RecommandDTO>> getRecommandList(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		Map<String, RecommandDTO> result = new HashMap<>();
		try {
			result.put("result", rService.getRecommand(mDTO.getMid(), mDTO.getCsno()));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/* *Author : 남승현
	 * 기능 : 클래스 구매 시, 관련 상품을 불러옴
	 * 매개변수 : 클래스 아이디, 클래스 수량, 클래스 이름
	 * 기타 : 클래스를 담는 경우, 관련 상품 데이터를 불러옴 
	 */
	@PostMapping(value = "/class/recommendations", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, List<IngredientDTO>>> RecommandForClass(
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO,@RequestBody Map<String,List<String>>map) {
		Map<String, List<IngredientDTO>> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			List<String> pamounts = map.get("pamounts");
			List<String> pnames = map.get("pnames");
			if (mDTO.getCsno() != null) {
				sService.cartInsert(pids, pamounts, Integer.parseInt(mDTO.getCsno()));
				sseEmitters.insert(mDTO.getCsno(),mDTO.getMid(),mDTO.getMname(), pnames);
			} else {
				cService.cartInsert(pids, pamounts, mDTO.getMid());
			}
			result.put("result", rService.getIngredients(mDTO.getMid(),mDTO.getCsno(),pids.get(0)));
			return new ResponseEntity<Map<String, List<IngredientDTO>>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, List<IngredientDTO>>>(HttpStatus.BAD_REQUEST);
		}
	}

}
