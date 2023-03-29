package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.admin.AdminEventVO;

@Mapper
public interface AdminEventMapper {

	List<AdminEventVO> getEventList(AdminEventVO event);

	void voteList(AdminEventVO event);

}
