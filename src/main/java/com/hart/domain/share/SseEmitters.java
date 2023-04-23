package com.hart.domain.share;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.member.ClubAuthMemberDTO;

import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 3. 25.
 * @FileName: SseEmitters.java
 * @author : 남승현
 * @설명 : SSE 기능 구현에 필요한 자료구조 및 메서드를 모아놓은 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       SseEmitters 구현
 *     </pre>
 */
@Component
@Log4j2
public class SseEmitters {

	private final ConcurrentHashMap<String, CopyOnWriteArrayList<CustomEmitter>> emitters = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, CopyOnWriteArrayList<ClubAuthMemberDTO>> users = new ConcurrentHashMap<>();
	
	/* *Author : 남승현
	 * 기능 : 같은 공유 장바구니 번호에 대해 SseEmitter를 추가하는 메서드
	 * 매개변수 : 공유 장바구니 번호, 해당 사용자의 SseEmitter, 사용자 정보
	 * 기타 : 공유 장바구니 번호에 대해, SseEmiiter 추가 및 등록된 사용자로의 추가
	 */
	public SseEmitter add(String csno, SseEmitter emitter, ClubAuthMemberDTO mDTO) {
		if (!emitters.containsKey(csno)) {
			emitters.put(csno, new CopyOnWriteArrayList<>());
			users.put(csno, new CopyOnWriteArrayList<>());
		}
		if(!users.get(csno).contains(mDTO)&&mDTO.getCsno()==null) {
			emitters.get(csno).forEach(emmiter ->{
				try {
					emmiter.getSse().send(SseEmitter.event().name("join").data(mDTO.getMname()));
				}catch (Exception e) {
					log.info(e.getMessage());
				}catch (Error e) {
					throw new RuntimeErrorException(e);
				}
			});
		}
		CustomEmitter cEmiiter = CustomEmitter.builder().mid(mDTO.getMid()).mname(mDTO.getMname()).sse(emitter).build();
		this.emitters.get(csno).add(cEmiiter);
		this.users.get(csno).add(mDTO);
		emitter.onCompletion(() -> {
			this.emitters.get(csno).remove(cEmiiter); // 만료되면 리스트에서 삭제
		});
		emitter.onTimeout(() -> {
			emitter.complete();
		});

		return emitter;
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니에 상품 추가, 이벤트를 다른 사용자들에게 보내는 메서드
	 * 매개변수 : 공유 장바구니 번호, 사용자 아이디, 추가한 사용자명, 상품 이름 목록
	 * 기타 : 상품 추가 이벤트와 추가된 상품명에 대한 데이터를 담아 보냄
	 */
	public void insert(String csno, String mid, String mname, List<String> pnames) {
		InsertInfoDTO iDTO = InsertInfoDTO.builder().mid(mid).pname(pnames.get(0)).count(pnames.size() - 1).mname(mname)
				.build();
		emitters.get(csno).forEach(emmiter -> {
			try {
				if (!emmiter.getMid().equals(mid))
				emmiter.getSse().send(SseEmitter.event().name("insert").data(iDTO));
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		});
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 상품 수량 변경 알림 메서드
	 * 매개변수 : 공유 장바구니 번호, CartInsertDTO(상품 아이디, 상품 수량) 
	 * 기타 : 상품 수량이 변경된 상품에 대한 정보를 담아, 공유 장바구니 사용자에게 보냄
	 */
	public void update(String csno, CartInsertDTO cDTO) {

		emitters.get(csno).forEach(emitter -> {
			log.info(emitter);
			try {
				emitter.getSse().send(SseEmitter.event().name("update").data(cDTO));
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		});
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 상품 수량 삭제 알림 메서드
	 * 매개변수 : 공유 장바구니 번호, 사용자 아이디, 사용자 이름, 상품 아이디 목록, 상품 이름 목록
	 * 기타 : 변경한 사용자의 이름과 함께 상품 목록 데이터를 담아서 공유 장바구니 사용자에게 보냄
	 */
	public void remove(String csno, String mid, String mname,List<String> pids,List<String> pnames) {
		RemovesInfoDTO rDTO = RemovesInfoDTO.builder()
								.mid(mid)
								.mname(mname)
								.pids(pids)
								.pname(pnames.get(0))
								.count(pids.size()-1)
								.build();
		emitters.get(csno).forEach(emitter -> {
			try {
				if(!emitter.getMid().equals(mid)) emitter.getSse().send(SseEmitter.event().name("remove").data(rDTO));
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		});
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 삭제 이벤트 알림 메서드
	 * 매개변수 : 공유 장바구니 번호, 사용자 아이디, 사용자 이름
	 * 기타 : 공유 장바구니 삭제 혹은 탈퇴 시 데이터를 담아 공유 장바구니 사용자에게 보냄
	 */
	public void deleteCarts(String csno, String mid, String mname) {

		emitters.get(csno).forEach(emitter -> {
			log.info(emitter);
			try {
				if(!emitter.getMid().equals(mid)) emitter.getSse().send(SseEmitter.event().name("delete").data(mid));	
				
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				log.info(e.getMessage());
			}

		});

		users.get(csno).forEach(mDTO -> {
			mDTO.setCsno(null);
		});
		emitters.remove(csno);

	}
}