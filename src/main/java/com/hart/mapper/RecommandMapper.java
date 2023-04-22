package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.recommand.IngredientDTO;
import com.hart.domain.recommand.RecommandDTO;

/**
 * @since : 2023. 3. 27.
 * @FileName: RecommandMapper.java
 * @author : 남승현
 * @설명 : 추천 관련 Mapper Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 27.     남승현       RecommandMapper 구현
 *     </pre>
 */
@Mapper
public interface RecommandMapper {
	// 자신의 장바구니 상품에 대해 추천을 받는 메서드
	public RecommandDTO getMyRecommand(String mid) throws SQLException;
	
	// 공유 장바구니 상품에 대해 추천을 받는 메서드
	public RecommandDTO getShareRecommand(String csno) throws SQLException;
	
	// 상품 관련 레시피, 클래스를 반환하는 메서드
	public RecommandDTO getProductRecommand(String pid) throws SQLException;
	
	// 자신의 장바구니 상품에 대해 추천을 받는 메서드
	public List<IngredientDTO> getIngredients(String lcid)throws SQLException;
}
