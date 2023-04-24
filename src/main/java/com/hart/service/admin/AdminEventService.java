package com.hart.service.admin;

import java.util.List;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.event.EventListVO;

/**
 * @since : 2023. 03. 29.
 * @FileName: AdminEventMapper.java
 * @author : 이승규
 * @설명 : 관리자 이벤트 Service
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 29.     이승규       voteList 구현
 * 2023. 04. 03.     이승규       getList 구현
 * 2023. 04. 03.     이승규       getTotalCount 구현
 * 2023. 04. 03.     이승규       getEventList 구현
 * 2023. 04. 03.     이승규       getVoteList 구현
 * 2023. 04. 04.     이승규       recipeRegister 구현
 * 2023. 04. 06.     이승규       getEventManageList 구현
 * 2023. 04. 06.     이승규       getEventTotalCount 구현
 * 2023. 04. 06.     이승규       eventRegister 구현
 * 2023. 04. 06.     이승규       eventModify 구현
 *     </pre>
 */
public interface AdminEventService {
	//레시피 투표하기 리스트 등록/취소
	void voteList(AdminEventVO event);
	// 이벤트 레시피 리스트 가져오기
	List<AdminEventVO> getList(Criteria cri);
	// 이벤트 레시피 리스트 갯수 가져오기	
	int getTotalCount(Criteria cri);
	//이벤트 카테고리 리스트 가져오기
	List<AdminEventVO> getEventList();
  // 투표하기 리스트 가져오기
	List<AdminEventVO> getVoteList(Criteria cri);
	// 고객레시피를 레시피에 INSERT
	void recipeRegister(AdminEventVO event);
	// 이벤트 관리 리스트 가져오기
	List<EventListVO> getEventManageList(Criteria cri);
	// 이벤트 전체 리스트 갯수 가져오기
	int getEventTotalCount();
	// 새로운 이벤트 등록
  void eventRegister(EventListVO event);
	// 이벤트 수정
  void eventModify(EventListVO event);

}
