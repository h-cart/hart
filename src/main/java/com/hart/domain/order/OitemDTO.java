package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 20.
 * @FileName: OitemDTO.java
 * @author : 남승현
 * @설명 : 주문 조회 시 필요한 데이터를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       OitemDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OitemDTO {
	
	private int oid;
	private String pid;
	private String pname;
	private String pimg;
	private int rvcheck;
	private int oamount;
	private int pprice;
}
