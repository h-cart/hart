package com.hart.domain.recommand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 3. 28.
 * @FileName: RecipeCategoryDTO.java
 * @author : 남승현
 * @설명 : 추천 레시피 목록에 대한, 카테고리만을 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 28.     남승현       RecipeCategoryDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Builder
public class RecipeCategoryDTO {
	private String rcano;
	private String rcname;
}
