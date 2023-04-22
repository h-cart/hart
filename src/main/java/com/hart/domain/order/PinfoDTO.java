package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 21.
 * @FileName: PinfoDTO.java
 * @author : 남승현
 * @설명 : 주문에서 활용되는 상품 정보를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 21.     남승현       PinfoDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PinfoDTO {

	private String pid;
	private String pimg;
	private String pname;
	private int pprice;
	private int pdiscount;
	private int mcamount;
	private int totalPrice;
	private int discountPrice;
}
