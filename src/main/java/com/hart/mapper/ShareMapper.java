package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.cart.CClassDTO;
import com.hart.domain.cart.CProductDTO;
import com.hart.domain.cart.CartInsertDTO;
import com.hart.domain.share.ShareDTO;

/**
 * @since : 2023. 3. 25.
 * @FileName: ShareMapper.java
 * @author : 남승현
 * @설명 : 공유 장바구니 관련 Mapper Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       ShareMapper 구현
 *     </pre>
 */
@Mapper
public interface ShareMapper {
	// 공유 장바구니 생성 메서드
	int create(ShareDTO sDTO)throws SQLException;
	
	// 공유 장바구니 정보 반환 메서드
	ShareDTO getInfo(String mid)throws SQLException;
	
	// 공유 요청 수락 시, 비밀번호 대조에 필요한 메서드
	ShareDTO getInfoWithKey(ShareDTO sDTO)throws SQLException;
	
	// 장바구니 공유 요청 시, 활용하는 메서드
	int ShareCsno(ShareDTO sDTO)throws SQLException;
	
	// 공유 장바구니 상품 추가 메서드
	int insertCarts(@Param("cDTO") CartInsertDTO cDTO,@Param("csno") int csno) throws SQLException;
	
	// 공유 장바구니 상품 목록 반환 메서드
	List<CProductDTO> getProducts(String csno)throws SQLException;
	
	// 공유 장바구니 클래스 목록 반환 메서드
	List<CClassDTO> getLClass(String csno)throws SQLException;
	
	// 공유 장바구니 상품 수량 변경 메서드
	int updateAmount(@Param("cDTO")CartInsertDTO cDTO, @Param("csno")int csno) throws SQLException;
	
	// 공유 장바구니 상품 삭제 메서드
	int removeCart(@Param("pids")List<String> pids, @Param("csno")int csno) throws SQLException;
	
	// 공유 장바구니, 생성자 확인 여부 메서드
	int isOwner(String mid) throws SQLException;
	
	// 공유 장바구니 삭제 시, 공유받은 사용자들 초기화 시키는 메서드
	int deleteAll(@Param("mid")String mid, @Param("csno") String csno);
	
	// 공유 장바구니 참여 취소 시, 개인이 나갈때 활용되는 메서드
	int deleteOne(String mid)throws SQLException;
	
	// 공유 장바구니 참여 취소 시, 개인이 나갈때 활용되는 메서드
	int sameProducts(@Param("pid") String pid,@Param("csno") String csno)throws SQLException;
}
