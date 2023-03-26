package com.hart.service.order;

import java.util.List;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.OrderTotalDTO;

public interface OrderService {
	
	OrderTotalDTO getInfo(List<String> pids, List<Integer> pamounts)throws Exception;
	ClubAuthMemberDTO insertOrder(ClubAuthMemberDTO mDTO, OrderInsertDTO oDTO)throws Exception;
}
