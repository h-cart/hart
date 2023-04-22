package com.hart.domain.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 14.
 * @FileName: CartInsertDTO.java
 * @author : 남승현
 * @설명 : 장바구니에 추가되는 상품에 대한 데이터를 담은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartInsertDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartInsertDTO {
	
	private String pid;
	private int pamount;
	
}
