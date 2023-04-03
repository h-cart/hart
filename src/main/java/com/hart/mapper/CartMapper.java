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
	
	public int insertCarts(@Param("cDTO") CartInsertDTO cDTO,@Param("mid") String mid) throws SQLException;
	public int isExistProduct(String pid)throws SQLException;
	public int isExistClass(String lcid)throws SQLException;
	public List<CProductDTO> getProducts(String mid)throws SQLException;
	public List<CClassDTO> getLClass(String mid)throws SQLException;
	public int updateAmount(@Param("cDTO")CartInsertDTO cDTO, @Param("mid")String mid) throws SQLException;
	public int removeCart(@Param("pids")List<String> pids, @Param("mid")String mid) throws SQLException;	
	public int sameProducts(@Param("pid") String pid,@Param("mid") String mid)throws SQLException;
	
}
