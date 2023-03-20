package com.hart.service.share;

import java.util.List;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;

public interface ShareService {
	
	ShareDTO createCart(String mid)throws Exception;
	int update(CartInsertDTO cDTO, int csno) throws Exception;
	ShareDTO getInfoWithKey(ShareDTO sDTO) throws Exception;
	CartDTO getCarts(String csno) throws Exception;
	
	int CartInsert(List<String> pids,List<String> pamounts,int csno) throws Exception;
	int deleteProducts(List<String> pids, int csno) throws Exception;
	
	ShareDTO getInfo(String mid) throws Exception;
}
