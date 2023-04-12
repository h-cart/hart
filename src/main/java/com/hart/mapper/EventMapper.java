package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeDTO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
import com.hart.domain.product.ProductsVO;

@Mapper
public interface EventMapper {

	List<ProductsVO> getProducts(String keyword) throws SQLException;
	int selectCrid();
	//레시피 내용 저장
	int registerRecipe(@Param("seq")int seq,@Param("recipe")CRecipeVO recipe);
	int registerContent(@Param("seq")int seq,@Param("content") CRContentVO content);
	int registerIngredient(@Param("seq")int seq,@Param("ingredient")CRIngredientVO ingredient);
	EventListVO getEventList(int evid);
	List<CRecipeVO> getVoteList(int evid);
	void toVote(EventVoteVO ev);
	CRecipeVO getDetail(EventVoteVO ev);
	Integer checkVote(EventVoteVO event);
	EventListVO getEventId();

}
