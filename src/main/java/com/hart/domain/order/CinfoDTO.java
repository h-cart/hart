package com.hart.domain.order;

import com.hart.domain.cart.CClassDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 20.
 * @FileName: CinfoDTO.java
 * @author : 남승현
 * @설명 : 주문시 필요한, 클래스 관련 데이터를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CinfoDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CinfoDTO {

	private String lcid;
	private String lcday;
	private int lcstatus;
	private String lcteacher;
	private String lcname;
	private String lcimg;
	private int lcprice;
	private int pamount;

}
