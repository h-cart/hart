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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.share.ShareDTO;
import com.hart.domain.share.SseEmitters;
import com.hart.service.cart.CartService;
import com.hart.service.share.ShareService;

import lombok.extern.log4j.Log4j2;

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

	@PostMapping(value = "/insert", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> insertCart(@RequestBody Map<String, List<String>> map,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		
		Map<String, String> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			List<String> pamounts = map.get("pamounts");
			if(mDTO.getCsno()!=null) {
				sService.CartInsert(pids, pamounts, Integer.parseInt(mDTO.getCsno()));
				//sseEmitters.update(mDTO.getCsno(),cDTO);
			}else {
				String mid = mDTO == null ? "skarns23@gmail.com" : mDTO.getMid();
				cService.CartInsert(pids, pamounts, mid);
			}
			result.put("result", "success");
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);

		} catch (Exception e) {
			result.put("result", e.getMessage());
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/get", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, CartDTO>> getCarts(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		
		Map<String, CartDTO> map = new HashMap<>();
		try {
			if(mDTO.getCsno()==null) map.put("result", cService.getCarts(mDTO.getMid()));
			else map.put("result", sService.getCarts(mDTO.getCsno()));
			return new ResponseEntity<Map<String, CartDTO>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("result", null);
			return new ResponseEntity<Map<String, CartDTO>>(map, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updateAmount", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> updateAmount(@RequestBody CartInsertDTO cDTO,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		Map<String, String> map = new HashMap<>();
		int result = -1;
		try {
			if(mDTO.getCsno() != null) {
				result = sService.update(cDTO, Integer.parseInt(mDTO.getCsno()));
				sseEmitters.update(mDTO.getCsno(),cDTO);
			}else {
			result = cService.updateAmount(cDTO, mDTO.getMid());
			}
		} catch (Exception e) {
			log.info(e);
		}


		String msg = result == 1 ? "success" : "fail";
		map.put("result", msg);
		return new ResponseEntity<Map<String, String>>(map, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/removes", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, String>> removes(@RequestBody Map<String, List<String>> map,
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		int count = 0;
		String msg = "";
		Map<String, String> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			// 공유 장바구니 없는 경우 일반 장바구니 삭제 로직
			if (mDTO.getCsno() == null) {
				count = cService.deleteProducts(pids, mDTO.getMid());
				msg = "상품 삭제";
			} else { // 공유 장바구니 있는 경우 공유 장바구니 삭제 로직 넣기
				count = sService.deleteProducts(pids, Integer.parseInt(mDTO.getCsno()));
				msg = "공유 상품 삭제";
				sseEmitters.remove(mDTO.getCsno(), pids);
			}
			result.put("result", msg);
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(e.getMessage());
			result.put("result", e.getMessage());
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.BAD_REQUEST);
		}

	}

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
	
	
	
	@GetMapping(value = "/sse/{csno}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)  
    public ResponseEntity<SseEmitter> connect(@PathVariable("csno")String csno) {  
        SseEmitter emitter = new SseEmitter(60*60*60L);  
        sseEmitters.add(csno,emitter);
        try {  
            emitter.send(SseEmitter.event()  
                    .name("connect")  
                    .data("connected!"));  
        } catch (IOException e) {  
            throw new RuntimeException(e);  
        }  
        return ResponseEntity.ok(emitter);  
    }  




}
