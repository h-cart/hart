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
import com.hart.domain.event.EventVoteVO;
import com.hart.domain.product.RecipeVO;
import com.hart.mapper.AdminEventMapper;
import com.hart.service.event.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	private AdminEventMapper mapper;
	@Autowired
	private EventService eventService;

	@Override
	public void voteList(AdminEventVO event) {
		mapper.voteList(event);

	}

	@Override
	public List<AdminEventVO> getList(Criteria cri) {
		return mapper.getEventList(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<AdminEventVO> getEventList() {

		return mapper.getEventCateList();
	}

	@Override
	public List<AdminEventVO> getVoteList(Criteria cri) {
		return mapper.getVoteList(cri);
	}

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

}
