package com.hart.domain.order;

import java.util.List;

import com.hart.domain.cart.CProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 21.
 * @FileName: OrderTotalDTO.java
 * @author : 남승현
 * @설명 : 주문에서 활용되는 상품 데이터 목록과 클래스 데이터 목록을 합쳐놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 21.     남승현       OrderTotalDTO 구현
 *     </pre>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTotalDTO {
	
	private List<CinfoDTO> cLists;
	private List<PinfoDTO> pLists;
	
}
