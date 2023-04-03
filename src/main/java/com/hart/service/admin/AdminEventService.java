package com.hart.service.admin;

import java.sql.SQLException;
import java.util.List;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.product.ProductsVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;

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
	

	
}
