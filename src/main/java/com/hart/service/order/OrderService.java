package com.hart.service.order;

import java.util.List;
import java.util.Map;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.OrderTotalDTO;
import com.hart.domain.order.SearchDTO;
import com.hart.domain.order.SearchResultDTO;

/**
 * @since : 2023. 3. 20.
 * @FileName: OrderService.java
 * @author : 남승현
 * @설명 : 장바구니 관련 OrderService Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       OrderService 구현
 *     </pre>
 */
public interface OrderService {
	// 주문에 필요한 상품 정보를 불러오는 메서드
	OrderTotalDTO getInfo(List<String> pids, List<Integer> pamounts)throws Exception;

	// 주문 데이터를 삽입하는 메서드
	Map<String,Object> insertOrder(ClubAuthMemberDTO mDTO, OrderInsertDTO oDTO)throws Exception;
	
	// 주문 취소 메서드
	public int orderCancle(String mid, int oid,int social) throws Exception;
	
	// 주문관련 정보를 불러오는 메서드
	public OinfoDTO getOrder(String mid, int oid) throws Exception;
	
	// 주문 조회 메서드
	public List<SearchResultDTO> searchOrders(SearchDTO sDTO) throws Exception;
	
	
}
