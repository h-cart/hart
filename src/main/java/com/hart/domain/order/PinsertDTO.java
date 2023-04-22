package com.hart.domain.order;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

/**
 * @since : 2023. 3. 21.
 * @FileName: PinsertDTO.java
 * @author : 남승현
 * @설명 : 주문 시, 필요한 상품 정보만을 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 21.     남승현       PinsertDTO 구현
 *     </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PinsertDTO {
	
	private String pid;
	private int pamount;
	
}
