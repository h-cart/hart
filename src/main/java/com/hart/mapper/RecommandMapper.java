package com.hart.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.recommand.RecommandDTO;

@Mapper
public interface RecommandMapper {
	
	public RecommandDTO getMyRecommand(String mid) throws SQLException;
	public RecommandDTO getShareRecommand(String csno) throws SQLException;
	public RecommandDTO getProductRecommand(String pid) throws SQLException;
}
