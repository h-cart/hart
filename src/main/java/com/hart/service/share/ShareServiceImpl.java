package com.hart.service.share;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;
import com.hart.mapper.CartMapper;
import com.hart.mapper.ShareMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ShareServiceImpl implements ShareService{
	
	@Autowired
	private ShareMapper sMapper;
	
	@Autowired
	private CartMapper cMapper;
	
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

	@Override
	public ShareDTO getInfoWithKey(ShareDTO sDTO) throws Exception {
		try {
			return sMapper.getInfoWithKey(sDTO);
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int update(CartInsertDTO cDTO, int csno) throws Exception {
		try {
			return sMapper.updateAmount(cDTO, csno);
		}catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public CartDTO getCarts(String csno) throws Exception {
		
		try {
			CartDTO cDTO = CartDTO.builder()
							.pLists(sMapper.getProducts(csno))
							.build();
			return cDTO;
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		
	}

	@Transactional
	@Override
	public int CartInsert(List<String> pids,List<String> pamounts ,int csno) throws Exception {
		int result = 0;
		for (int i=0;i<pids.size();i++) {
			String pid = pids.get(i);
			CartInsertDTO cDTO = CartInsertDTO.builder()
								.pid(pid)
								.pamount(Integer.parseInt(pamounts.get(i)))
								.build();
			if (pid.startsWith("S") || pid.startsWith("O")) {
				try {
					if (cMapper.isExistProduct(pid) == 1) {
						result += sMapper.insertCarts(cDTO, csno);
					} else {
						throw new Exception("pid가 존재하지 않음");
					}
				} catch (Exception e) {
					log.info(e.getMessage());
					throw e;
				}
			}
		}
		return result;
	}
	
	@Override
	public int deleteProducts(List<String> pids, int csno) throws Exception {
		try {
			boolean flag = true;
			for (String pid : pids) {
				int check = pid.startsWith("S")||pid.startsWith("O") ? 
						cMapper.isExistProduct(pid) : cMapper.isExistClass(pid);
				if(check==0) {
					flag = false;
					break;
				}
			}
			return flag ? sMapper.removeCart(pids, csno) : -1;
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		
	}
	
	
	
	

}
