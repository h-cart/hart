package com.hart.service.order;

import java.util.List;
import java.util.Map;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.OrderTotalDTO;
import com.hart.domain.order.SearchDTO;
import com.hart.domain.order.SearchResultDTO;

public interface OrderService {
	
	OrderTotalDTO getInfo(List<String> pids, List<Integer> pamounts)throws Exception;
	Map<String,Object> insertOrder(ClubAuthMemberDTO mDTO, OrderInsertDTO oDTO)throws Exception;
	public int orderCancle(String mid, int oid) throws Exception;
	public OinfoDTO getOrder(String mid, int oid) throws Exception;
	public List<SearchResultDTO> searchOrders(SearchDTO sDTO) throws Exception;
	
}
