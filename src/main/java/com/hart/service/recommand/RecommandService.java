package com.hart.service.recommand;

import java.util.List;

import com.hart.domain.recommand.IngredientDTO;
import com.hart.domain.recommand.RecommandDTO;

/**
 * @since : 2023. 3. 27.
 * @FileName: RecommandService.java
 * @author : 남승현
 * @설명 : 추천 관련 RecommandService Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 27.     남승현       RecommandService 구현
 *     </pre>
 */
public interface RecommandService {
	
	// 사용자 장바구니 상품 관련, 추천 레시피 및 클래스 불러오는 기능
	public RecommandDTO getRecommand(String mid,String csno) throws Exception;
	
	// 상품 관련, 추천 레시피 및 클래스 불러오는 기능
	public RecommandDTO RecommandForProduct(String pid) throws Exception;
	
	// 사용자 장바구니 상품 관련, 추천 레시피 및 클래스 불러오는 기능
	public List<IngredientDTO> getIngredients(String mid, String csno,String lcid)throws Exception;
}
