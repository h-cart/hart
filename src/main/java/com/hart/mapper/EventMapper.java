package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeDTO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
import com.hart.domain.product.ProductsVO;


/**
 * @since : 2023. 03. 17.
 * @FileName: EventMapper.java
 * @author : 이승규
 * @설명 : 공모전 Mapper
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 17.     이승규       getProducts 구현
 * 2023. 03. 22.     이승규       selectCrid 구현
 * 2023. 03. 22.     이승규       registerRecipe 구현
 * 2023. 03. 22.     이승규       registerContent 구현
 * 2023. 03. 22.     이승규       registerIngredient 구현
 * 2023. 03. 22.     이승규       getEventList 구현
 * 2023. 03. 30.     이승규       getVoteList 구현
 * 2023. 03. 30.     이승규       toVote 구현
 * 2023. 03. 30.     이승규       getDetail 구현	
 * 2023. 04. 12.     이승규       checkVote 구현
 * 2023. 03. 12.     이승규       getEventId 구현
 *     </pre>
 */
@Mapper
public interface EventMapper {
	// 상품 리스트 가져오기
	List<ProductsVO> getProducts(String keyword) throws SQLException;
	// 고객 레시피 아이디 가져오기
	int selectCrid();
	// 레시피 내용 저장
	int registerRecipe(@Param("seq")int seq,@Param("recipe")CRecipeVO recipe);
	// 레시피 컨텐츠 저장
	int registerContent(@Param("seq")int seq,@Param("content") CRContentVO content);
	// 레시피 재료 저장
	int registerIngredient(@Param("seq")int seq,@Param("ingredient")CRIngredientVO ingredient);
	// 이벤트 리스트 가져오기
	EventListVO getEventList(int evid);
	// 투표 리스트 가져오기
	List<CRecipeVO> getVoteList(int evid);
	// 투표하기
	void toVote(EventVoteVO ev);
	// 투표리스트 상세 가져오기
	CRecipeVO getDetail(EventVoteVO ev);
	// 투표여부 확인하기
	Integer checkVote(EventVoteVO event);
	// 이벤트 아이디 가져오기
	EventListVO getEventId();

}
