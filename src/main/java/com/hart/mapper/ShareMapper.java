package com.hart.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.share.ShareDTO;

@Mapper
public interface ShareMapper {
	
	int create(ShareDTO sDTO)throws SQLException;
	ShareDTO getInfo(String mid)throws SQLException;
	int ShareCsno(ShareDTO sDTO)throws SQLException;
	
}
