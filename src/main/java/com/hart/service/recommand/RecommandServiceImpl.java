package com.hart.service.recommand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.recommand.IngredientDTO;
import com.hart.domain.recommand.RecipeCategoryDTO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.domain.recommand.RrecipeDTO;
import com.hart.mapper.CartMapper;
import com.hart.mapper.RecommandMapper;
import com.hart.mapper.ShareMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RecommandServiceImpl implements RecommandService {

	@Autowired
	private RecommandMapper rMapper;

	@Autowired
	private CartMapper cMapper;

	@Autowired
	private ShareMapper sMapper;

	@Transactional
	@Override
	public RecommandDTO getRecommand(String mid, String csno) throws Exception {
		try {
		RecommandDTO result = null;
		if (csno == null)
			result = rMapper.getMyRecommand(mid);
		else
			result = rMapper.getShareRecommand(csno);
		if (result == null)
			return result;
		if (result.getRecipes() != null) {
			result.setRcates(getCategory(result));
		}
		if(!result.getRecipes().isEmpty()) {
			for(int i=0;i<result.getRecipes().size();i++) {
				result.getRecipes().get(i).setItems(removeItem(mid,csno,result.getRecipes().get(i).getItems())) ;
			}
		}
		if(!result.getLives().isEmpty()) {
			for(int i=0;i<result.getLives().size();i++) {
				result.getLives().get(i).setItems(removeItem(mid,csno,result.getLives().get(i).getItems())) ;
			}
		}
		return result;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

	@Override
	public List<IngredientDTO> getIngredients(String mid, String csno, String lcid) throws Exception {
		List<IngredientDTO> result = removeItem(mid, csno,rMapper.getIngredients(lcid));
		
		return result;
	}

	@Transactional
	@Override
	public RecommandDTO RecommandForProduct(String pid) throws Exception {
		RecommandDTO result = rMapper.getProductRecommand(pid);
		if (result == null)
			return result;
		if (result.getRecipes() != null) {
			result.setRcates(getCategory(result));
		}
		
		return result;
	}

	public String dateFormatter(String odate) {
		return odate.split(" ")[0].substring(2).replace("-", "/");
	}

	public List<RecipeCategoryDTO> getCategory(RecommandDTO result) {
		List<RecipeCategoryDTO> rcates = new ArrayList<>();
		for (RrecipeDTO rDTO : result.getRecipes()) {
			rcates.add(RecipeCategoryDTO.builder().rcano(rDTO.getRcano()).rcname(rDTO.getRccategory()).build());
		}
		return rcates;
	}

	@Transactional
	public List<IngredientDTO> removeItem(String mid, String csno, List<IngredientDTO> list)throws Exception {
		List<IngredientDTO> result = new ArrayList<>();
		System.out.println(mid+", "+csno);
		if (csno != null) {
			for (IngredientDTO temp : list) {
				try {
					if (sMapper.sameProducts(temp.getPid(), csno) == 0) {
						result.add(temp);
					}
				} catch (Exception e) {
					throw e;
				}
			}
		}else {
			for (IngredientDTO temp : list) {
				try {
					if (cMapper.sameProducts(temp.getPid(), mid) == 0) {
						result.add(temp);
					}
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return result;

	}

}
