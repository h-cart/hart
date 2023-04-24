package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 21.
 * @FileName: SearchDTO.java
 * @author : 남승현
 * @설명 : 주문 조회시, 활용되는 정보를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 21.     남승현       SearchDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDTO {
	
	private String mid;
	private String sdate;
	private String edate;
	private int isCancel;
}
