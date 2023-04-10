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
import com.hart.domain.order.SearchDTO;
import com.hart.domain.order.SearchResultDTO;

@Mapper
public interface OrderMapper {
	
	public PinfoDTO pInfos(String pid)throws SQLException;
	public CinfoDTO cInfos(String lcid)throws SQLException;
	public int insertOrder(OinfoDTO oDTO) throws SQLException;
	public int insertProduct(@Param("pLists") List<PinsertDTO> pLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	public int insertClass(@Param("cLists")List<CinsertDTO> cLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	public int intoMyClass(@Param("cLists")List<CinsertDTO> cLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	public int checkClass(@Param("mid")String mid, @Param("cLists")List<CinsertDTO> cLists)throws SQLException;
	public int orderCancle(int oid)throws SQLException;
	public OinfoDTO getOrder(@Param("oid") int oid,@Param("mid") String mid)throws SQLException;
	public List<SearchResultDTO> getOrders(SearchDTO sDTo) throws SQLException;
	
}
