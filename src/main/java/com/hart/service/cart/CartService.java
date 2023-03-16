package com.hart.service.cart;

import java.util.List;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;

public interface CartService {
	int CartInsert(List<String> pids,List<String> pamounts,String mid) throws Exception;
	CartDTO getCarts(String mid) throws Exception;
	int updateAmount(CartInsertDTO cDTO, String mid)throws Exception;
	int deleteProducts(List<String> pids, String mid) throws Exception;
}
