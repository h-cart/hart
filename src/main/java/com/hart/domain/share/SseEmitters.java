package com.hart.domain.share;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.log4j.Log4j2;

@Component  
@Log4j2 
public class SseEmitters {  
  
    private final Map<String,List<SseEmitter>> emitters = new ConcurrentHashMap<>();  
  
    public SseEmitter add(String csno,SseEmitter emitter) {
    	if(!emitters.containsKey(csno))emitters.put(csno, new ArrayList<>());
        this.emitters.get(csno).add(emitter);
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
    
    public void update(String csno) {
    
    	emitters.get(csno).forEach(emitter -> {
    		log.info(emitter);
    		try {
    			emitter.send(SseEmitter.event()
    					.name("update")
    					.data(""));
    		}catch (Error e) {
    			throw new RuntimeErrorException(e);
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }
}