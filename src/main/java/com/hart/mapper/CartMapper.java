package com.hart.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.CartInsertDTO;

@Mapper
public interface CartMapper {
	
	int insertCarts(@Param("cDTO") CartInsertDTO cDTO,@Param("mid") String mid) throws SQLException;
	int isExistProduct(String pid)throws SQLException;
	int isExistClass(String lcid)throws SQLException;
}
