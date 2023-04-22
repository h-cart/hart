package com.hart.service.cart;

import java.util.List;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;

/**
 * @since : 2023. 3. 14.
 * @FileName: CartService.java
 * @author : 남승현
 * @설명 : 장바구니 관련 Service Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartService 구현
 *     </pre>
 */
public interface CartService {
	// 장바구니 상품 추가 메서드
	int cartInsert(List<String> pids,List<String> pamounts,String mid) throws Exception;
	
	// 장바구니 상품 불러오는 메서드
	CartDTO getCarts(String mid) throws Exception;
	
	// 장바구니 상품 추가 메서드
	int updateAmount(CartInsertDTO cDTO, String mid)throws Exception;
	
	// 장바구니 상품 삭제 메서드
	int deleteProducts(List<String> pids, String mid) throws Exception;
}
