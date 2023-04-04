package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;

@Mapper
public interface AdminEventMapper {

	void voteList(AdminEventVO event);

	List<AdminEventVO> getEventList(Criteria cri);

	int getTotalCount(Criteria cri);


	List<AdminEventVO> getEventCateList();

	List<AdminEventVO> getVoteList(Criteria cri);

	void recipeRegister(AdminEventVO event);

}
