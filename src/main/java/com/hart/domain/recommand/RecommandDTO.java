package com.hart.domain.recommand;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since : 2023. 3. 28.
 * @FileName: RecommandDTO.java
 * @author : 남승현
 * @설명 : 추천 데이터를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 28.     남승현       RecommandDTO 구현
 *     </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommandDTO {
	
	private String pid;
	private String pname;
	private String pimg;
	private String stag;
	private List<RecipeCategoryDTO> rcates;
	private List<RrecipeDTO> recipes;
	private List<RliveClassDTO> lives;
}
