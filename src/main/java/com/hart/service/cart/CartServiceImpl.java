package com.hart.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.mapper.CartMapper;

import lombok.extern.log4j.Log4j2;


/**
 * @since : 2023. 3. 14.
 * @FileName: CartServiceImpl.java
 * @author : 남승현
 * @설명 : 장바구니 관련 Service 구현 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartServiceImpl 구현
 *     </pre>
 */
@Service
@Log4j2
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cMapper;
	
	/* *Author : 남승현
	 * 기능 : 장바구니 상품 추가 
	 * 매개변수 : 상품 아이디, 상품 수량, 사용자 아이디
	 * 기타 : 장바구니 싱품 추가 시, 상품 존재 여부 확인, 같은 상품이 담겨있는 지 여부 판단을 진행함으로
	 * 		 @Transactional 어노테이션 활용
	 */
	@Transactional
	@Override
	public int cartInsert(List<String> pids,List<String> pamounts ,String mid) throws Exception {
		int result = 0;
		for (int i=0;i<pids.size();i++) {
			String pid = pids.get(i);
			CartInsertDTO cDTO = CartInsertDTO.builder()
								.pid(pid)
								.pamount(Integer.parseInt(pamounts.get(i)))
								.build();
			if(cMapper.sameProducts(pid, mid)==1) {
				throw new Exception("-1");
			}
			if (pid.startsWith("S") || pid.startsWith("O")) {
				try {
					if (cMapper.isExistProduct(pid) == 1) {
						result += cMapper.insertCarts(cDTO, mid);
					} else {
						throw new Exception("pid가 존재하지 않음");
					}
				} catch (Exception e) {
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
					throw e;
				}
				
			}
		}
		return result;
	}

	/* *Author : 남승현
	 * 기능 : 장바구니 상품 수량 변경
	 * 매개변수 : 상품 아이디, 상품 수량, 사용자 아이디
	 */
	@Override
	public int updateAmount(CartInsertDTO cDTO, String mid) throws Exception {
		try {
			return cMapper.updateAmount(cDTO, mid);
		}catch (Exception e) {
			throw e;
		}
	}

	/* *Author : 남승현
	 * 기능 : 장바구니 상품 불러오기
	 * 매개변수 : 사용자 아이디
	 * 기타 : Transactional 어노테이션 활용
	 */
	@Transactional
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

	/* *Author : 남승현
	 * 기능 : 장바구니 상품 삭제
	 * 매개변수 : 상품 아이디, 사용자 아이디
	 */
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
