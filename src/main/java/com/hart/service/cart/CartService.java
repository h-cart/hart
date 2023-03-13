package com.hart.service.cart;

import java.util.List;

import com.hart.domain.CartInsertDTO;

public interface CartService {
	int CartInsert(List<String> pids,List<String> pamounts,String mid) throws Exception;
}
