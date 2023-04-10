package com.hart.service.recommand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.recommand.RecipeCategoryDTO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.domain.recommand.RrecipeDTO;
import com.hart.mapper.RecommandMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RecommandServiceImpl  implements RecommandService{

	@Autowired
	private RecommandMapper rMapper;

	
	@Transactional
	@Override
	public RecommandDTO getRecommand(String mid,String csno) throws Exception{
		RecommandDTO result =null;
		if(csno == null) result = rMapper.getMyRecommand(mid);
		else  result = rMapper.getShareRecommand(csno);
		if(result.getRecipes()!=null) {
			List<RecipeCategoryDTO> rcates = new ArrayList<>();
			for(RrecipeDTO rDTO : result.getRecipes()) {
				rcates.add(RecipeCategoryDTO.builder()
							.rcano(rDTO.getRcano())
							.rcname(rDTO.getRccategory())
							.build());
			}
			result.setRcates(rcates);
		}
		System.out.println(result);
		return result;
	}
	
	public String dateFormatter(String odate) {
		return odate.split(" ")[0].substring(2).replace("-","/");
	}
	
}
