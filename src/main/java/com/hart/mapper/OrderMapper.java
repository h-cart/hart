package com.hart.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.cart.CProductDTO;
import com.hart.domain.order.CinfoDTO;

@Mapper
public interface OrderMapper {
	
	CProductDTO pInfos(String pid)throws SQLException;
	CinfoDTO cInfos(String lcid)throws SQLException;
	
}
