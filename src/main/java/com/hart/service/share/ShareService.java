package com.hart.service.share;

import com.hart.domain.share.ShareDTO;

public interface ShareService {
	
	ShareDTO createCart(String mid)throws Exception;
}
