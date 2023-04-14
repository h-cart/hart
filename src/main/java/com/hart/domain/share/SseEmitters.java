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

import io.netty.util.internal.ConcurrentSet;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class SseEmitters {

	private final ConcurrentHashMap<String, CopyOnWriteArrayList<CustomEmitter>> emitters = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, CopyOnWriteArrayList<ClubAuthMemberDTO>> users = new ConcurrentHashMap<>();
	
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
					e.printStackTrace();
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

	public void insert(String csno, String mid, String mname, List<String> pnames) {
		InsertInfoDTO iDTO = InsertInfoDTO.builder().mid(mid).pname(pnames.get(0)).count(pnames.size() - 1).mname(mname)
				.build();
		log.info(iDTO);
		emitters.get(csno).forEach(emmiter -> {
			try {
				if (!emmiter.getMid().equals(mid))
				emmiter.getSse().send(SseEmitter.event().name("insert").data(iDTO));
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void update(String csno, CartInsertDTO cDTO) {

		emitters.get(csno).forEach(emitter -> {
			log.info(emitter);
			try {
				emitter.getSse().send(SseEmitter.event().name("update").data(cDTO));
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void remove(String csno, String mid, String mname,List<String> pids,List<String> pnames) {
		RemovesInfoDTO rDTO = RemovesInfoDTO.builder()
								.mid(mid)
								.mname(mname)
								.pids(pids)
								.pname(pnames.get(0))
								.count(pids.size()-1)
								.build();
		emitters.get(csno).forEach(emitter -> {
			log.info(emitter);
			try {
				if(!emitter.getMid().equals(mid)) emitter.getSse().send(SseEmitter.event().name("remove").data(rDTO));
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	public void deleteCarts(String csno, String mid, String mname) {

		emitters.get(csno).forEach(emitter -> {
			log.info(emitter);
			try {
				if(!emitter.getMid().equals(mid)) emitter.getSse().send(SseEmitter.event().name("delete").data(mid));	
				
			} catch (Error e) {
				throw new RuntimeErrorException(e);
			} catch (IOException e) {
				e.printStackTrace();
			}

		});

		users.get(csno).forEach(mDTO -> {
			mDTO.setCsno(null);
		});
		emitters.remove(csno);

	}
}