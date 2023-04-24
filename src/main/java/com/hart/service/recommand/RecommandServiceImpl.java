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


/**
 * @since : 2023. 3. 27.
 * @FileName: RecommandServiceImpl.java
 * @author : 남승현
 * @설명 : 추천 관련 RecommandService 구현 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 27.     남승현       RecommandServiceImpl 구현
 *     </pre>
 */
@Service
@Log4j2
public class RecommandServiceImpl implements RecommandService {

	@Autowired
	private RecommandMapper rMapper;

	@Autowired
	private CartMapper cMapper;

	@Autowired
	private ShareMapper sMapper;

	/* *Author : 남승현
	 * 기능 : 사용자 장바구니 기반, 레시피 및 클래스 추천
	 * 매개변수 : 사용자 아이디, 공유 장바구니 번호
	 */
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
			throw e;
		}
		
		
	}

	/* *Author : 남승현
	 * 기능 : 사용자 아이디, 공유 장바구니 번호, 클래스 아이디
	 * 매개변수 : 관련 상품 추가를 진행하나, 내부적으로 중복된 상품에 대해 띄워주지 않기 위해, 중복 상품을 삭제하는 메서드
	 */
	@Transactional
	@Override
	public List<IngredientDTO> getIngredients(String mid, String csno, String lcid) throws Exception {
		List<IngredientDTO> result = removeItem(mid, csno,rMapper.getIngredients(lcid));
		
		return result;
	}

	/* *Author : 남승현
	 * 기능 : 상품 관련 레시피 및 클래스를 추천하는 기능
	 * 매개변수 : 상품 아이디
	 */
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

	/* *Author : 남승현
	 * 기능 : 레시피 카테고리를 담기 위한 메서드
	 * 매개변수 : 추천 결과 데이터
	 */
	public List<RecipeCategoryDTO> getCategory(RecommandDTO result) {
		List<RecipeCategoryDTO> rcates = new ArrayList<>();
		for (RrecipeDTO rDTO : result.getRecipes()) {
			rcates.add(RecipeCategoryDTO.builder().rcano(rDTO.getRcano()).rcname(rDTO.getRccategory()).build());
		}
		return rcates;
	}

	/* *Author : 남승현
	 * 기능 : 레시피 카테고리를 담기 위한 메서드
	 * 매개변수 : 추천 결과 데이터
	 */
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
