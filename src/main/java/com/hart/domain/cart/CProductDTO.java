package com.hart.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @since : 2023. 3. 14.
 * @FileName: CProductDTO.java
 * @author : 남승현
 * @설명 : 장바구니에 필요한 상품관련 데이터 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CProductDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CProductDTO {
	
	private String pid;
	private String pimg;
	private String pname;
	private int pprice;
	private int pdiscount;
	private int mcamount;
}
