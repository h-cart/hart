package com.hart.domain.recommand;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 28.
 * @FileName: RrecipeDTO.java
 * @author : 남승현
 * @설명 : 추천 라이브 클래스 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 28.     남승현       RrecipeDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RrecipeDTO {
	private String rid;
	private String rtitle;
	private String rlevel;
	private String rtime;
	private String rimg;
	private String rcano;
	private String rccategory;
	private List<IngredientDTO> items;
	
}
