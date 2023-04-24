package com.hart.domain.share;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @since : 2023. 3. 25.
 * @FileName: ShareDTO.java
 * @author : 남승현
 * @설명 : 공유 장바구니에 대한 정보를 담은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       ShareDTO 구현
 *     </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class ShareDTO {
	
	private int csno;
	private String cskey;
	private String mid;
}
