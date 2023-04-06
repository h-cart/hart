package com.hart.service.admin;

import java.util.List;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.event.EventListVO;

/**
 * @since : 2023. 3. 28.
 * @FileName: EventService.java
 * @author : 이승규
 * @설명 :
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 16.     이승규
 *     </pre>
 */
public interface AdminEventService {

	void voteList(AdminEventVO event);

	List<AdminEventVO> getList(Criteria cri);

	int getTotalCount(Criteria cri);

	List<AdminEventVO> getEventList();

	List<AdminEventVO> getVoteList(Criteria cri);

	void recipeRegister(AdminEventVO event);

	List<EventListVO> getEventManageList(Criteria cri);

	int getEventTotalCount();

  void eventRegister(EventListVO event);

}
