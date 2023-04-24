package com.hart.service.event;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.product.ProductsVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
import com.hart.mapper.EventMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 03. 17.
 * @FileName: EventServiceImpl.java
 * @author : 이승규
 * @설명 : 공모전 EventServiceImpl
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
@Service
@Log4j2
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventMapper mapper;

	// 상품 리스트 가져오기
	@Override
	public List<ProductsVO> getList(String keyword) throws SQLException {
		return mapper.getProducts(keyword);
	}
	// 레시피 내용 저장
	@Override
	@Transactional
	public void register(CRecipeVO recipe, List<CRContentVO> contents, List<CRIngredientVO> ingredients) {
		int seq = mapper.selectCrid();

		mapper.registerRecipe(seq, recipe);
		for (CRContentVO content : contents) {
			mapper.registerContent(seq, content);
		}
		for (CRIngredientVO ingredient : ingredients) {
			mapper.registerIngredient(seq, ingredient);
		}

	}
	// 이벤트 리스트 가져오기
	@Override
	public EventListVO getEvent(int evid) {

		return mapper.getEventList(evid);
	}
	// 투표 리스트 가져오기
	@Override
	public List<CRecipeVO> getVoteList(int evid) {

		return mapper.getVoteList(evid);
	}
	// 투표하기
	@Override
	public void voteRecipe(EventVoteVO ev) {
		mapper.toVote(ev);

	}
	// 투표리스트 상세 가져오기
	@Override
	public CRecipeVO getRecipeDetail(EventVoteVO ev) {
		CRecipeVO cr = mapper.getDetail(ev);
		log.info("service cr =========" + cr);
		return cr;
	}
	// 투표여부 확인하기
	@Override
	public Integer checkVote(EventVoteVO event) {
		return mapper.checkVote(event);
	}
	// 이벤트 아이디 가져오기
	@Override
	public EventListVO getEventId() {
		return mapper.getEventId();
	}

}
