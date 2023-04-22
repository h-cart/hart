package com.hart.domain.share;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 25.
 * @FileName: InsertInfoDTO.java
 * @author : 남승현
 * @설명 : 공유하기 장바구니에 상품을 담는 경우 활용되는 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       InsertInfoDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertInfoDTO {
	
	private String mid;
	private String mname;
	private String pname;
	private int count;
}
