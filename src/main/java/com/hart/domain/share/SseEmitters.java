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

@Component  
@Log4j2 
public class SseEmitters {  

    private final ConcurrentHashMap<String,CopyOnWriteArrayList<SseEmitter>> emitters = new ConcurrentHashMap<>();  
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<ClubAuthMemberDTO>> users = new ConcurrentHashMap<>();
    public SseEmitter add(String csno,SseEmitter emitter,ClubAuthMemberDTO mDTO) {
    	if(!emitters.containsKey(csno)) {
    		emitters.put(csno, new CopyOnWriteArrayList<>());
    		users.put(csno,new CopyOnWriteArrayList<>());
    	}
        this.emitters.get(csno).add(emitter);
        this.users.get(csno).add(mDTO);
        log.info("new emitter added: {}", emitter);  
        log.info("emitter list size: {}", emitters.get(csno).size());  
        emitter.onCompletion(() -> {  
            log.info("onCompletion callback");  
            this.emitters.get(csno).remove(emitter);    // 만료되면 리스트에서 삭제
        });  
        emitter.onTimeout(() -> {  
            log.info("onTimeout callback");  
            emitter.complete();  
        });  
  
        return emitter;  
    }  
    
    public void update(String csno,CartInsertDTO cDTO) {
    
    	emitters.get(csno).forEach(emitter -> {
    		log.info(emitter);
    		try {
    			emitter.send(SseEmitter.event()
    					.name("update")
    					.data(cDTO));
    		}catch (Error e) {
    			throw new RuntimeErrorException(e);
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }
    public void remove(String csno, List<String> pids) {
    	emitters.get(csno).forEach(emitter -> {
    		log.info(emitter);
    		try {
    			emitter.send(SseEmitter.event()
    					.name("remove")
    					.data(pids));
    		}catch (Error e) {
    			throw new RuntimeErrorException(e);
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }
    
    public void deleteCarts(String csno) {
    	
    	emitters.get(csno).forEach(emitter ->{
    		log.info(emitter);
    		try {
    			emitter.send(SseEmitter.event()
    						.name("delete")
    						.data("load"));
    		}catch (Error e) {
    			throw new RuntimeErrorException(e);
    		}catch (IOException e) {
    			e.printStackTrace();
    		}
    	
    	});
    	users.get(csno).forEach(mDTO -> {
    		mDTO.setCsno(null);
    	});
    	emitters.remove(csno);
    
    }
}