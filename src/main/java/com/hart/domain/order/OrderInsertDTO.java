package com.hart.domain.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 21.
 * @FileName: OrderInsertDTO.java
 * @author : 남승현
 * @설명 : 주문 시, 필요한 주문 정보 / 상품 정보 / 클래스 정보 / 주소 저장 여부를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 21.     남승현       OrderInsertDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInsertDTO {
		
	private OinfoDTO oinfo;
	private List<PinsertDTO> pLists;
	private List<CinsertDTO> cLists;
	private boolean saveAddr;
	
}
