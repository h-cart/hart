package com.hart.service.share;

import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;

public interface ShareService {
	
	ShareDTO createCart(String mid)throws Exception;
	int update(CartInsertDTO cDTO, int csno) throws Exception;
	ShareDTO getInfoWithKey(ShareDTO sDTO) throws Exception;
	
}
