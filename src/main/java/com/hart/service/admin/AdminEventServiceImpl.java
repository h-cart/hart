package com.hart.service.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
import com.hart.domain.product.RecipeVO;
import com.hart.mapper.AdminEventMapper;
import com.hart.service.event.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Service
@Log4j2
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	private AdminEventMapper mapper;
	@Autowired
	private EventService eventService;

	//레시피 투표하기 리스트 등록/취소
	@Override
	public void voteList(AdminEventVO event) {
		mapper.voteList(event);

	}
	// 이벤트 레시피 리스트 가져오기
	@Override
	public List<AdminEventVO> getList(Criteria cri) {
		return mapper.getEventList(cri);
	}
	// 이벤트 레시피 리스트 갯수 가져오기	
	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	// 이벤트 카테고리 리스트 가져오기
	@Override
	public List<AdminEventVO> getEventList() {

		return mapper.getEventCateList();
	}
	// 이벤트 투표 리스트 가져오기
	@Override
	public List<AdminEventVO> getVoteList(Criteria cri) {
		return mapper.getVoteList(cri);
	}
	// 고객레시피를 레시피에 INSERT
	@Override
	@Transactional
	public void recipeRegister(AdminEventVO event) {
		EventVoteVO ev = new EventVoteVO();
		ev.setCrid(event.getCrid());

		CRecipeVO recipe = eventService.getRecipeDetail(ev);
		RecipeVO re = new RecipeVO(recipe);
		mapper.insertCRecipe(re.getRid(), re);

		int s = 1;
		for (CRContentVO content : re.getRecipeContent()) {
			content.setStep("Step" + s++);
			content.setCrimg("/event/api/display?imgName=" + content.getCrimg());
			log.info("여기 동작1---------" + content);
			mapper.insertCRContent(re.getRid(), content);

		}
		for (CRIngredientVO ingredient : recipe.getCrecipeIngredientVo()) {
			if (!ingredient.getPid().equals("undefined")) {

				mapper.insertCRIngredient(re.getRid(), ingredient);

			}

		}

	}

	// 이벤트 관리 리스트 가져오기
	@Override
	public List<EventListVO> getEventManageList(Criteria cri) {
		return mapper.getEventManageList(cri);
	}
	// 이벤트 전체 리스트 갯수 가져오기
	@Override
	public int getEventTotalCount() {
		return mapper.getEventTotalCount();
	}
	// 새로운 이벤트 등록
	@Override
	public void eventRegister(EventListVO event) {
		mapper.eventRegister(event);
		
	}
	// 이벤트 수정
	@Override
	public void eventModify(EventListVO event) {
		mapper.eventModify(event);
		
	}

}
