package com.hart.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
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
	public void recipeRegister(AdminEventVO event) {
		EventVoteVO ev = new EventVoteVO();
		ev.setCrid(event.getCrid());
		CRecipeVO recipe = eventService.getRecipeDetail(ev);

		RecipeVO re = new RecipeVO();
		re.setRid("CR" + recipe.getCrid());
		re.setRtitle(recipe.getCrtitle());
		re.setRimg(recipe.getCrMimg());
		re.setRmingredient(recipe.getCrmingredient());
		re.setRlevel(recipe.getCrlevel());
		re.setRtime(recipe.getCrtime());
		re.setRclick(0);
		re.setRcano(recipe.getCrcano());
		re.setRdetail(recipe.getCrdetail());

		List<CRIngredientVO> cList = recipe.getCrecipeIngredientVo();

		String tmp = "";
		for (CRIngredientVO a : cList) {

			tmp += a.getIname() + " " + a.getCricount() + " \n";

		}
		re.setRreadyingredient(tmp);
		log.info(re);

	}

}
