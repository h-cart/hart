package com.hart.service.recommand;

import com.hart.domain.recommand.RecommandDTO;

public interface RecommandService {
	
	public RecommandDTO getRecommand(String mid,String csno) throws Exception;
}
