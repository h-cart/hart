package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.order.CinfoDTO;
import com.hart.domain.order.CinsertDTO;
import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.PinfoDTO;
import com.hart.domain.order.PinsertDTO;

@Mapper
public interface OrderMapper {
	
	PinfoDTO pInfos(String pid)throws SQLException;
	CinfoDTO cInfos(String lcid)throws SQLException;
	int insertOrder(OinfoDTO oDTO) throws SQLException;
	int insertProduct(@Param("pLists") List<PinsertDTO> pLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	int insertClass(@Param("cLists")List<CinsertDTO> cLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	int intoMyClass(@Param("cLists")List<CinsertDTO> cLists)throws SQLException;
	int checkClass(@Param("mid")String mid, @Param("cLists")List<CinsertDTO> cLists)throws SQLException;
	int orderCancle(int oid)throws SQLException;
	OinfoDTO getOrder(@Param("oid") int oid,@Param("mid") String mid)throws SQLException;
}
