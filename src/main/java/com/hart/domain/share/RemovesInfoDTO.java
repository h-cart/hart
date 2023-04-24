package com.hart.domain.share;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 25.
 * @FileName: RemovesInfoDTO.java
 * @author : 남승현
 * @설명 : 공유 장바구니 상품 삭제시 활용되는 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       RemovesInfoDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemovesInfoDTO {
	
	private String mid;
	private String mname;
	private String pname;
	private List<String> pids;
	private int count;
}
