package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.product.RecipeVO;

@Mapper
public interface AdminEventMapper {

	void voteList(AdminEventVO event);

	List<AdminEventVO> getEventList(Criteria cri);

	int getTotalCount(Criteria cri);

	List<AdminEventVO> getEventCateList();

	List<AdminEventVO> getVoteList(Criteria cri);

	void insertCRecipe(@Param("rid") String rid, @Param("re") RecipeVO re);

	void insertCRContent(@Param("rid") String rid, @Param("content") CRContentVO content);

	void insertCRIngredient(@Param("rid") String rid, @Param("ingredient") CRIngredientVO ingredient);

	List<EventListVO> getEventManageList(Criteria cri);

	int getEventTotalCount();

  void eventRegister(EventListVO event);

}
