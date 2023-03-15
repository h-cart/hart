package com.hart.service.share;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.share.ShareDTO;
import com.hart.mapper.ShareMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ShareServiceImpl implements ShareService{
	
	@Autowired
	private ShareMapper sMapper;

	@Transactional
	@Override
	public ShareDTO createCart(String mid)throws Exception {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 20;
		Random random = new Random();

		String generatedString = random.ints(leftLimit,rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(targetStringLength)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		try {
			sMapper.create(ShareDTO.builder().cskey(generatedString)
											.mid(mid).build());
			ShareDTO result = sMapper.getInfo(mid);
			log.info(result);
			return result;
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	

}
