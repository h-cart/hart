package com.hart.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @since : 2023. 3. 14.
 * @FileName: CClassDTO.java
 * @author : 남승현
 * @설명 : 장바구니에 필요한 클래스관련 데이터 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CClassDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CClassDTO {
	
	private String lcid;
	private String lcday;
	private int lcstatus;
	private String lcteacher;
	private String lcname;
	private String lcimg;
	private int lcprice;
}
