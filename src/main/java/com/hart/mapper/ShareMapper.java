package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.cart.CClassDTO;
import com.hart.domain.cart.CProductDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;

@Mapper
public interface ShareMapper {
	
	int create(ShareDTO sDTO)throws SQLException;
	ShareDTO getInfo(String mid)throws SQLException;
	ShareDTO getInfoWithKey(ShareDTO sDTO)throws SQLException;
	int ShareCsno(ShareDTO sDTO)throws SQLException;
	
	int insertCarts(@Param("cDTO") CartInsertDTO cDTO,@Param("csno") int csno) throws SQLException;
	List<CProductDTO> getProducts(String csno)throws SQLException;
	List<CClassDTO> getClasss(String mid)throws SQLException;
	int updateAmount(@Param("cDTO")CartInsertDTO cDTO, @Param("csno")int csno) throws SQLException;
	int removeCart(@Param("pids")List<String> pids, @Param("csno")int csno) throws SQLException;
	
}
