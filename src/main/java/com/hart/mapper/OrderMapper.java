package com.hart.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.order.CinfoDTO;
import com.hart.domain.order.PinfoDTO;

@Mapper
public interface OrderMapper {
	
	PinfoDTO pInfos(String pid)throws SQLException;
	CinfoDTO cInfos(String lcid)throws SQLException;
	
}
