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

/**
 * @since : 2023. 3. 25.
 * @FileName: ShareServiceImpl.java
 * @author : 남승현
 * @설명 : 공유 관련 ShareService 구현 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 25.     남승현       ShareServiceImpl 구현
 *     </pre>
 */
@Service
@Log4j2
public class ShareServiceImpl implements ShareService{
	
	@Autowired
	private ShareMapper sMapper;
	
	@Autowired
	private CartMapper cMapper;
	
	/* *Author : 남승현
	 * 기능 : 비밀번호 생성을 통한 공유 장바구니 생성
	 * 매개변수 : 사용자 아이디
	 */
	@Transactional
	@Override
	public ShareDTO createCart(String mid)throws Exception {
		int leftLimit = '0'	; // numeral '0'
		int rightLimit = 'z'; // letter 'z'
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
			return result;
		}catch (Exception e) {
			throw e;
		}
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 번호, 비밀번호를 통한 공유 장바구니 정보 불러오는 기능
	 * 매개변수 : ShareDTO(공유 장바구니 번호, 비밀번호, 사용자 아이디)
	 */
	@Override
	public ShareDTO getInfoWithKey(ShareDTO sDTO) throws Exception {
		try {
			return sMapper.getInfoWithKey(sDTO);
		}catch (Exception e) {
			throw e;
		}
	}
	

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 정보를 불러오는 기능
	 * 매개변수 : 사용자 아이디
	 */
	@Override
	public ShareDTO getInfo(String mid) throws Exception {
		try {
			return sMapper.getInfo(mid);
		}catch (Exception e) {
			throw e;
		}
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 상품 수량 변경 메서드
	 * 매개변수 : CartInsertDTO(상품 아이디, 상품 수량), 공유 장바구니 번호
	 */
	@Override
	public int update(CartInsertDTO cDTO, int csno) throws Exception {
		try {
			return sMapper.updateAmount(cDTO, csno);
		}catch (Exception e) {
			throw e;
		}
	}
	
	/* *Author : 남승현
	 * 기능 : 공유 장바구니 상품 불러오는 메서드
	 * 매개변수 : 공유 장바구니 번호
	 */
	@Override
	public CartDTO getCarts(String csno) throws Exception {
		
		try {
			CartDTO cDTO = CartDTO.builder()
							.pLists(sMapper.getProducts(csno))
							.cLists(sMapper.getLClass(csno))
							.build();
			return cDTO;
		}catch (Exception e) {
			throw e;
		}
		
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 상품 추가 메서드
	 * 매개변수 : 상품 아이디, 상품 수량, 공유 장바구니 번호
	 */
	@Transactional
	@Override
	public int cartInsert(List<String> pids,List<String> pamounts ,int csno) throws Exception {
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
					throw e;
				}
			}else {
				try {
					if(cMapper.isExistClass(pid)==1) {
						result += sMapper.insertCarts(cDTO, csno);
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
	 * 기능 : 공유 장바구니 상품 삭제 메서드
	 * 매개변수 : 상품 아이디, 공유 장바구니 번호
	 */
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
			throw e;
		}
		
	}
	
	/* *Author : 남승현
	 * 기능 : 장바구니 공유 요청 메서드
	 * 매개변수 : ShareDTO(공유 장바구니 번호, 비밀번호, 사용자 아이디), 공유 장바구니 번호
	 */
	@Transactional
	@Override
	public boolean shareCsno(ShareDTO sDTO,String csno) throws Exception {
		boolean result = false;
		try {
			if(sMapper.isOwner(sDTO.getMid())==1&&csno !=null) {
				result = true;
				sMapper.deleteAll(sDTO.getMid(), csno);
			}
			sMapper.ShareCsno(sDTO);
			return result;
		}catch (Exception e) {
			throw e;
		}
	}

	/* *Author : 남승현
	 * 기능 : 공유 장바구니 취소 메서드
	 * 매개변수 : 사용자 아이디, 공유 자압구니 번호
	 */
	@Override
	public boolean cancleShare(String mid,String csno) throws Exception {
		boolean result = false;

		try {
			if(sMapper.isOwner(mid)==1) {
				result = true;
				sMapper.deleteAll(mid, csno);
			}else sMapper.deleteOne(mid);
			return result;
		}catch (Exception e) {
			throw e;
		}

	}
	
	
	
	

}
