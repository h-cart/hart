package com.hart.domain.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 21.
 * @FileName: SearchResultDTO.java
 * @author : 남승현
 * @설명 : 주문 내역 조회 결과를 반환하는 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 21.     남승현       SearchResultDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResultDTO {
	
	private int oid;
	private String odate;
	private int opayment;
	private int ostatus;
	private int ousedpoint;
	private List<OitemDTO> oitems;
}
