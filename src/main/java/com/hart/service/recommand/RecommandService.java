package com.hart.service.recommand;

import java.util.List;

import com.hart.domain.recommand.IngredientDTO;
import com.hart.domain.recommand.RecommandDTO;

public interface RecommandService {
	
	public RecommandDTO getRecommand(String mid,String csno) throws Exception;
	public RecommandDTO RecommandForProduct(String mid, String csno,String pid) throws Exception;
	public List<IngredientDTO> getIngredients(String mid, String csno,String lcid)throws Exception;
}
