package com.hart.service.recommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.recommand.RecommandDTO;
import com.hart.mapper.RecommandMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RecommandServiceImpl  implements RecommandService{

	@Autowired
	private RecommandMapper rMapper;

	
	@Transactional
	@Override
	public RecommandDTO getRecommand(String mid,String csno) throws Exception{
		RecommandDTO result =null;
		if(csno == null) result = rMapper.getMyRecommand(mid);
		else  result = rMapper.getShareRecommand(csno);
		
		return result;
	}
	
	
}
