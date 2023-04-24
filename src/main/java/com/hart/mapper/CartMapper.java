package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.cart.CClassDTO;
import com.hart.domain.cart.CProductDTO;
import com.hart.domain.cart.CartInsertDTO;

/**
 * @since : 2023. 3. 14.
 * @FileName: CartMapper.java
 * @author : 남승현
 * @설명 : 장바구니 관련 Mapper Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 14.     남승현       CartMapper 구현
 *     </pre>
 */
@Mapper
public interface CartMapper {
	// 장바구니 상품 추가 메서드
	public int insertCarts(@Param("cDTO") CartInsertDTO cDTO,@Param("mid") String mid) throws SQLException;
	
	// 상품 테이블에 존재하는 상품 여부 확인 메서드
	public int isExistProduct(String pid)throws SQLException;
	
	// 클래스 테이블에 존재하는 클래스 여부 확인 메서드
	public int isExistClass(String lcid)throws SQLException;
	
	// 장바구니에 존재하는 상품정보를 가져오는 메서드
	public List<CProductDTO> getProducts(String mid)throws SQLException;
	
	// 장바구니에 존재하는 클래스정보를 가져오는 메서드
	public List<CClassDTO> getLClass(String mid)throws SQLException;
	
	// 장바구니 상품 수량 변경 메서드
	public int updateAmount(@Param("cDTO")CartInsertDTO cDTO, @Param("mid")String mid) throws SQLException;
	
	// 장바구니 상품 삭제 메서드
	public int removeCart(@Param("pids")List<String> pids, @Param("mid")String mid) throws SQLException;	
	
	// 장바구니에 존재하는 상품 여부를 확인하는 메서드
	public int sameProducts(@Param("pid") String pid,@Param("mid") String mid)throws SQLException;
	
}
