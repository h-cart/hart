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

/**
 * @since : 2023. 3. 19.
 * @FileName: OrderMapper.java
 * @author : 남승현
 * @설명 : 주문 관련 Mapper Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 19.     남승현       OrderMapper 구현
 *     </pre>
 */
@Mapper
public interface OrderMapper {
	
	// 주문에 활용되는 상품 정보를 불러오는 메서드
	public PinfoDTO pInfos(String pid)throws SQLException;
	
	// 주문에 활용되는 클래스 정보를 불러오는 메서드
	public CinfoDTO cInfos(String lcid)throws SQLException;
	
	// 주문 관련 데이터 삽입 및 주문번호를 반환하는 메서드
	public int insertOrder(OinfoDTO oDTO) throws SQLException;
	
	// 주문 번호에 대한 상품 정보를 넣는 메서드
	public int insertProduct(@Param("pLists") List<PinsertDTO> pLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	
	// 주문 번호에 대한 클래스 정보를 넣는 메서드
	public int insertClass(@Param("cLists")List<CinsertDTO> cLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	
	// 클래스 주문 후, 나의 클래스 내역에 넣는 메서드
	public int intoMyClass(@Param("cLists")List<CinsertDTO> cLists,@Param("oDTO") OinfoDTO oDTO)throws SQLException;
	
	// 클래스 주문 시, 나의 클래스 내역에 존재하는 지 여부 확인 메서드
	public int checkClass(@Param("mid")String mid, @Param("cLists")List<CinsertDTO> cLists)throws SQLException;
	
	// 주문 취소 메서드
	public int orderCancle(int oid)throws SQLException;
	
	// 클래스 주문 후, 나의 클래스 내역에 넣는 메서드
	public OinfoDTO getOrder(@Param("oid") int oid,@Param("mid") String mid)throws SQLException;
	
	// 주문 조회 시, 활용되는 메서
	public List<SearchResultDTO> getOrders(SearchDTO sDTo) throws SQLException;
	
}
