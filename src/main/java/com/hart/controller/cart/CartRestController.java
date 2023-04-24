package com.hart.controller.cart;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.domain.share.ShareDTO;
import com.hart.domain.share.SseEmitters;
import com.hart.service.cart.CartService;
import com.hart.service.recommand.RecommandService;
import com.hart.service.share.ShareService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import lombok.extern.log4j.Log4j2;


/**
 * @since : 2023. 3. 14.
 * @FileName: CartRestController.java
 * @author : 남승현
 * @설명 : 장바구니 CRUD 기능 관련 처리를 위한 RestController
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartRestController 구현
 * 2023. 3. 18.     남승현       공유 장바구니 관련 로직 추가
 *     </pre>
 */
@RestController
@RequestMapping("/capi")
@Log4j2
public class CartRestController {

	private final SseEmitters sseEmitters;

	public CartRestController(SseEmitters sseEmitters) {
		this.sseEmitters = sseEmitters;
	}

	@Autowired
	private CartService cService;

	@Autowired
	private ShareService sService;
	
	@Autowired
	private RecommandService rService;

	/* *Author : 남승현
	 * 기능 : 상품 장바구니 추가
	 * 매개변수 : 상품 아이디, 상품 갯수, 상품 이름
	 * 기타 : 넘어온 상품관련 데이터(아이디, 갯수, 이름)을 통해, 사용자의 개인 / 공유 장바구니에 넣음
	 * 		 공유 장바구니에 넣는 경우 SSE를 통해 다른 공유장바구니 사용자에게 이벤트와 데이터를 보내줌
	 */
	@PostMapping(value = "/insert", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, RecommandDTO>> insertCart(@RequestBody Map<String, List<String>> map,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		
		Map<String, RecommandDTO> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			List<String> pamounts = map.get("pamounts");
			List<String> pnames = map.get("pnames");
			if (mDTO.getCsno() != null) {
				sService.cartInsert(pids, pamounts, Integer.parseInt(mDTO.getCsno()));
				sseEmitters.insert(mDTO.getCsno(),mDTO.getMid(), mDTO.getMname(), pnames);
			} else {
				
				cService.cartInsert(pids, pamounts, mDTO.getMid());
			}
			
			result.put("result", rService.getRecommand(mDTO.getMid(), mDTO.getCsno()));
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/* *Author : 남승현
	 * 기능 : 장바구니 상품 불러오는 기능
	 * 매개변수 : X
	 * 기타 : 공유 장바구니 소유 여부에 따라, 공유 장바구니 상품 / 개인 장바구니 상품을 불러옴
	 */
	@PostMapping(value = "/get", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, CartDTO>> getCarts(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		Map<String, CartDTO> map = new HashMap<>();
		try {
			if (mDTO.getCsno() == null)
				map.put("result", cService.getCarts(mDTO.getMid()));
			else
				map.put("result", sService.getCarts(mDTO.getCsno()));
			return new ResponseEntity<Map<String, CartDTO>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("result", null);
			return new ResponseEntity<Map<String, CartDTO>>(map, HttpStatus.BAD_REQUEST);
		}
	}

	/* *Author : 남승현
	 * 기능 : 장바구니 상품 수량 변경 기능 
	 * 매개변수 : CartInsertDTO(상품 아이디, 수량)
	 * 기타 : 공유 장바구니 / 개인 장바구니에 대해, 담긴 상품에 대해 수량을 변경
	 */
	@PostMapping(value = "/updateAmount", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> updateAmount(@RequestBody CartInsertDTO cDTO,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		Map<String, String> map = new HashMap<>();
		int result = -1;
		String msg = "";
		try {
			if (mDTO.getCsno() != null) {
				result = sService.update(cDTO, Integer.parseInt(mDTO.getCsno()));
				sseEmitters.update(mDTO.getCsno(), cDTO);
			} else {
				result = cService.updateAmount(cDTO, mDTO.getMid());
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}

		msg = result == 1 ? "success" : msg;
		map.put("result", msg);
		return new ResponseEntity<Map<String, String>>(map, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	/* *Author : 남승현
	 * 기능 : 장바구니 상품 삭제 기능
	 * 매개변수 : 상품 아이디, 상품명
	 * 기타 : 공유 장바구니 / 개인 장바구니에 대한 상품을 삭제하는 기능 
	 * 		 공유 장바구니의 경우, 공유 장바구니 사용자들에게 SSE를 활용하여 이벤트 및 데이터를 보냄
	 */	
	@PostMapping(value = "/removes", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> removes(@RequestBody Map<String, List<String>> map,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		int count = 0;
		String msg = "";
		Map<String, String> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			List<String> pnames = map.get("pnames");
			// 공유 장바구니 없는 경우 일반 장바구니 삭제 로직
			if (mDTO.getCsno() == null) {
				count = cService.deleteProducts(pids, mDTO.getMid());
				msg = "상품 삭제";
			} else { // 공유 장바구니 있는 경우 공유 장바구니 삭제 로직 넣기
				count = sService.deleteProducts(pids, Integer.parseInt(mDTO.getCsno()));
				msg = "공유 상품 삭제";
				sseEmitters.remove(mDTO.getCsno(),mDTO.getMid(),mDTO.getMname(), pids,pnames);
			}
			result.put("result", msg);
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.put("result", e.getMessage());
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.BAD_REQUEST);
		}

	}


	/* *Author : 남승현
	 * 기능 : 공유 장바구니 생성 기능
	 * 매개변수 : X
	 * 기타 : 인증된 사용자에 대해, 공유 장바구니를 만든뒤, 해당 공유 장바구니 번호를 사용자 인스턴스에 설정해줌
	 */
	@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, ShareDTO>> createShare(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		if (mDTO == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		try {
			ShareDTO sDTO = sService.createCart(mDTO.getMid());
			mDTO.setCsno(String.valueOf(sDTO.getCsno()));
			Map<String, ShareDTO> map = new HashMap<>();
			map.put("result", sDTO);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니에 참여한 사용자들이 해당 공유 장바구니를 구독하도록 하는 기능 
	 * 매개변수 : X
	 * 기타 : 해당 사용자에 대한 SseEmitter 객체를 생성해, 공유 장바구니 번호를 Key로 갖는 Map에 Value 값으로 넣어줌
	 */
	@GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> connect(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		if(mDTO.getCsno()==null) return null; 
		SseEmitter emitter = new SseEmitter(60 * 60*60L);
		sseEmitters.add(mDTO.getCsno(), emitter, mDTO);
		
		try {
			emitter.send(SseEmitter.event().name("connect").data("connected!"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return ResponseEntity.ok(emitter);
	}


	/* *Author : 남승현
	 * 기능 : 공유 장바구니를 다른 사용자에게 공유하는 기능 
	 * 매개변수 : ShareDTO(공유 장바구니 번호, 공유 장바구니 비밀번호, 사용자 아이디)
	 * 기타 : 카카오톡 공유하기를 통해, 장바구니 공유 요청을 받은 사용자가 수락을 누른 경우 동작
	 * 		 기존 공유 장바구니에 대해, 삭제 혹은 탈퇴 로직은 처리한 뒤, 새로운 공유 장바구니 번호로 교체 
	 */
	@PostMapping(value = "/share", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> sharePermit(@AuthenticationPrincipal ClubAuthMemberDTO mDTO,
			@RequestBody ShareDTO sDTO) {
		Map<String, String> map = new HashMap<>();
		String msg = "";
		try {
			sDTO.setMid(mDTO.getMid());
			if (sService.shareCsno(sDTO, mDTO.getCsno())) {
				sseEmitters.deleteCarts(mDTO.getCsno(), mDTO.getMid(),mDTO.getMname());
			}
			sseEmitters.add(String.valueOf(sDTO.getCsno()),new SseEmitter(60*60*60L),mDTO);
			mDTO.setCsno(String.valueOf(sDTO.getCsno()));
			msg = "success";
		} catch (Exception e) {
			msg = "fail";
		}
		map.put("result", msg);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}


	/* *Author : 남승현
	 * 기능 : 공유 취소 기능 
	 * 매개변수 : X
	 * 기타 : 공유 장바구니 참여 사용자가 해당 공유에 대해, 탈퇴 혹은 취소하는 경우 실행되는 로직
	 */
	@PostMapping(value = "/cancle", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> cancleShare(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		Map<String, String> map = new HashMap<>();
		String msg = "";
		try {
			if (sService.cancleShare(mDTO.getMid(), mDTO.getCsno())) {
				sseEmitters.deleteCarts(mDTO.getCsno(), mDTO.getMid(),mDTO.getMname());
				msg = "all";
			} else {
				msg = "one";
				mDTO.setCsno(null);
			}

		} catch (Exception e) {
			map.put("result", e.getMessage());
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
		}
		map.put("result", msg);
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	/* *Author : 남승현
	 * 기능 : 공유 장바구니 소유자가 자신이 속한 공유 장바구니에 대한 정보를 불러오는 기능
	 * 매개변수 : X
	 * 기타 : 사용자가 소유한 공유 장바구니 정보를 가져와 반환함
	 */
	@GetMapping(value = "/getInfo", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String,ShareDTO>> getInfo(@AuthenticationPrincipal ClubAuthMemberDTO mDTO){
			Map<String,ShareDTO> result = new HashMap<>();
			try {
				ShareDTO sDTO = sService.getInfo(mDTO.getMid());
				result.put("result", sDTO);
				return new  ResponseEntity<Map<String,ShareDTO>>(result,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<Map<String,ShareDTO>>(HttpStatus.BAD_REQUEST);
			}
	}
	


}
