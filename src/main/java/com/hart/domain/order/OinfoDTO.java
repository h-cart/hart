package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 20.
 * @FileName: OinfoDTO.java
 * @author : 남승현
 * @설명 : 주문 관련 데이터를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       OinfoDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OinfoDTO {
	private int oid;
	private String oname;
	private int ozipcode;
	private String oaddress1;
	private String oaddress2;
	private int opayment;
	private int ousedpoint;
	private int odiscount;
	private String phone;
	private String mid;
	
	
}
