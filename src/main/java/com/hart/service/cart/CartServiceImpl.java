package com.hart.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.mapper.CartMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cMapper;

	@Transactional
	@Override
	public int CartInsert(List<String> pids,List<String> pamounts ,String mid) throws Exception {
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
						result += cMapper.insertCarts(cDTO, mid);
					} else {
						throw new Exception("pid가 존재하지 않음");
					}
				} catch (Exception e) {
					log.info(e.getMessage());
					throw e;
				}
			}else {
				try {
					if(cMapper.isExistClass(pid)==1) {
						result += cMapper.insertCarts(cDTO, mid);
					}else {
						throw new Exception("존재하지 않는 클래스 ");
						
					}
				}catch (Exception e) {
					log.info(e.getMessage());
					throw e;
				}
				
			}
		}
		return result;
	}

	@Override
	public int updateAmount(CartInsertDTO cDTO, String mid) throws Exception {
		try {
			return cMapper.updateAmount(cDTO, mid);
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	@Override
	public CartDTO getCarts(String mid) throws Exception {
		
		try {
			CartDTO cDTO = CartDTO.builder()
							.pLists(cMapper.getProducts(mid))
							.cLists(cMapper.getLClass(mid))
							.build();
			return cDTO;
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		
	}

	@Override
	public int deleteProducts(List<String> pids, String mid) throws Exception {
		try {
			return cMapper.removeCart(pids, mid);
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
		
	}

}
