package com.hart.service.share;

import java.util.List;

import com.hart.domain.cart.CartDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;

/**
 * @since : 2023. 3. 25.
 * @FileName: ShareService.java
 * @author : 남승현
 * @설명 : 추천 관련 ShareService 구현 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       ShareService 구현
 *     </pre>
 */
public interface ShareService {
	
	// 공유 장바구니 생성
	ShareDTO createCart(String mid)throws Exception;
	
	// 공유 장바구니, 상품 수량 변경
	int update(CartInsertDTO cDTO, int csno) throws Exception;
	
	// 공유 장바구니 번호와 비밀번호를 통한 정보 불러오기
	ShareDTO getInfoWithKey(ShareDTO sDTO) throws Exception;
	
	// 공유 장바구니 상품 불러오는 기능
	CartDTO getCarts(String csno) throws Exception;
	
	// 장바구니 공유
	boolean shareCsno(ShareDTO sDTO,String csno) throws Exception;
	
	// 공유 장바구니 상품 추가
	int cartInsert(List<String> pids,List<String> pamounts,int csno) throws Exception;
	
	// 공유 장바구니 상품 삭제
	int deleteProducts(List<String> pids, int csno) throws Exception;
	
	// 공유 장바구니 정보 불러오기
	ShareDTO getInfo(String mid) throws Exception;
	
	// 장바구니 공유 취소
	boolean cancleShare(String mid,String csno)throws Exception;
}
