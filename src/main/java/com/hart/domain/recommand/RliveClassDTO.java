package com.hart.domain.recommand;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 28.
 * @FileName: RliveClassDTO.java
 * @author : 남승현
 * @설명 : 추천 라이브 클래스 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 28.     남승현       RliveClassDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RliveClassDTO {
	
	private String lcid;
	private int lcstatus;
	private String lcname;
	private String lcteacher;
	private int lcprice;
	private String lcimg;
	private String lcday;
	private List<IngredientDTO> items;
}
