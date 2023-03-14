package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.cart.CClassDTO;
import com.hart.domain.cart.CProductDTO;
import com.hart.domain.cart.CartInsertDTO;

@Mapper
public interface CartMapper {
	
	int insertCarts(@Param("cDTO") CartInsertDTO cDTO,@Param("mid") String mid) throws SQLException;
	int isExistProduct(String pid)throws SQLException;
	int isExistClass(String lcid)throws SQLException;
	List<CProductDTO> getProducts(String mid)throws SQLException;
	List<CClassDTO> getClasss(String mid)throws SQLException;
}
