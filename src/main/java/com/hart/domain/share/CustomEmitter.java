package com.hart.domain.share;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 26.
 * @FileName: CustomEmitter.java
 * @author : 남승현
 * @설명 : 기존 SseEmitter에 필요한 데이터를 추가적으로 담은 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 26.     남승현       CustomEmitter 구현
 *     </pre>
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomEmitter {
	
	private String mid;
	private String mname;
	private SseEmitter sse;
}
