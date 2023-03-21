package com.hart.service.order;

import java.util.List;

import com.hart.domain.order.OrderTotalDTO;

public interface OrderService {
	
	OrderTotalDTO getInfo(List<String> pids, List<Integer> pamounts)throws Exception;
}
