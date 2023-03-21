package com.hart.service.event;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hart.domain.ProductsVO;
import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;
import com.hart.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventMapper mapper;

	@Override
	public List<ProductsVO> getList(String keyword) throws SQLException {
		return mapper.getProducts(keyword);
	}

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

}
