package com.hart.domain.recommand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @since : 2023. 3. 28.
 * @FileName: IngredientDTO.java
 * @author : 남승현
 * @설명 : 추천 레시피 및 클래스 관련 상품 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 28.     남승현       IngredientDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDTO {
		
	private String pid;
	private String pname;
	private String pimg;
	private int pprice;
	private int pdiscount;
	
}
