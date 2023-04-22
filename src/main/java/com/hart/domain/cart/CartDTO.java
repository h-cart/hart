package com.hart.domain.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @since : 2023. 3. 14.
 * @FileName: CartDTO.java
 * @author : 남승현
 * @설명 : 상품 리스트 및 클래스 리스트를 합친 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDTO {
	
	private List<CProductDTO> pLists;
	private List<CClassDTO> cLists;
	
}
