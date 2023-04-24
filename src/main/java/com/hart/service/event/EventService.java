package com.hart.service.event;

import java.sql.SQLException;
import java.util.List;

import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.product.ProductsVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;

/**
 * @since : 2023. 03. 17.
 * @FileName: EventService.java
 * @author : 이승규
 * @설명 : 공모전 EventService
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 17.     이승규       getList 구현
 * 2023. 03. 22.     이승규       register 구현
 * 2023. 03. 22.     이승규       getEvent 구현
 * 2023. 03. 30.     이승규       getVoteList 구현
 * 2023. 03. 30.     이승규       voteRecipe 구현
 * 2023. 03. 30.     이승규       getRecipeDetail 구현	
 * 2023. 04. 12.     이승규       checkVote 구현
 * 2023. 03. 12.     이승규       getEventId 구현
 *     </pre>
 */
public interface EventService {
	// 상품 리스트 가져오기
	List<ProductsVO> getList(String keyword) throws SQLException;
	// 레시피 내용 저장
	void register(CRecipeVO recipe, List<CRContentVO> content, List<CRIngredientVO> ingredient);
	// 이벤트 리스트 가져오기
	EventListVO getEvent(int evid);
	// 투표 리스트 가져오기
	List<CRecipeVO> getVoteList(int evid);
	// 투표하기
	void voteRecipe(EventVoteVO ev);
	// 투표리스트 상세 가져오기
	CRecipeVO getRecipeDetail(EventVoteVO ev);
	// 투표여부 확인하기
	Integer checkVote(EventVoteVO event);
	// 이벤트 아이디 가져오기
	EventListVO getEventId();
}
