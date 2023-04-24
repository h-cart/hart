package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.product.RecipeVO;


/**
 * @since : 2023. 03. 29.
 * @FileName: AdminEventMapper.java
 * @author : 이승규
 * @설명 : 관리자 이벤트 Mapper
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 29.     이승규       voteList 구현
 * 2023. 04. 03.     이승규       getEventList 구현
 * 2023. 04. 03.     이승규       getTotalCount 구현
 * 2023. 04. 03.     이승규       getEventCateList 구현
 * 2023. 04. 03.     이승규       getVoteList 구현
 * 2023. 04. 04.     이승규       insertCRecipe 구현
 * 2023. 04. 04.     이승규       insertCRContent 구현
 * 2023. 04. 04.     이승규       insertCRIngredient 구현
 * 2023. 04. 06.     이승규       getEventManageList 구현
 * 2023. 04. 06.     이승규       getEventTotalCount 구현
 * 2023. 04. 06.     이승규       eventRegister 구현
 * 2023. 04. 06.     이승규       eventModify 구현
 *     </pre>
 */
@Mapper
public interface AdminEventMapper {

	//레시피 투표하기 리스트 등록/취소
	void voteList(AdminEventVO event);
  // 이벤트 레시피 리스트 가져오기
	List<AdminEventVO> getEventList(Criteria cri);
	// 이벤트 레시피 리스트 갯수 가져오기
	int getTotalCount(Criteria cri);
	//이벤트 카테고리 리스트 가져오기
	List<AdminEventVO> getEventCateList();
	// 투표하기 리스트 가져오기
	List<AdminEventVO> getVoteList(Criteria cri);
	// 고객레시피를 레시피에 INSERT
	void insertCRecipe(@Param("rid") String rid, @Param("re") RecipeVO re);
	// 고객레시피 컨텐츠를 레시피 컨텐츠에 INSERT
	void insertCRContent(@Param("rid") String rid, @Param("content") CRContentVO content);
	// 고객레시피 재료를 레시피 재료에 INSERT
	void insertCRIngredient(@Param("rid") String rid, @Param("ingredient") CRIngredientVO ingredient);
	// 이벤트 관리 리스트 가져오기
	List<EventListVO> getEventManageList(Criteria cri);
	// 이벤트 전체 리스트 갯수 가져오기
	int getEventTotalCount();
	// 새로운 이벤트 등록
  void eventRegister(EventListVO event);
	// 이벤트 수정
	void eventModify(EventListVO event);

}
