package com.hart.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.member.ClubMember2;
import com.hart.domain.order.CinfoDTO;
import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.OrderTotalDTO;
import com.hart.domain.order.PinfoDTO;
import com.hart.domain.order.SearchDTO;
import com.hart.domain.order.SearchResultDTO;
import com.hart.mapper.CartMapper;
import com.hart.mapper.MemberMapper;
import com.hart.mapper.OrderMapper;

import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 3. 20.
 * @FileName: OrderServiceImpl.java
 * @author : 남승현
 * @설명 : 주문 관련 OrderService 구현 클래스
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       OrderServiceImpl 구현
 *     </pre>
 */
@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper oMapper;
	
	@Autowired
	private CartMapper cMapper;
	
	@Autowired
	private MemberMapper mMapper;
	

	/* *Author : 남승현
	 * 기능 : 주문 시 활용되는 메서드
	 * 매개변수 : 사용자 정보 인스턴스, 주문에 필요한 상품 정보
	 * 기타 : 사용자 주문 정보 저장 및 마일리지 변경 등 다수 기능 적용을 위한
	 * 		 @Transactional 어노테이션 활용
	 */
	@Transactional
	@Override
	public Map<String,Object> insertOrder(ClubAuthMemberDTO mDTO, OrderInsertDTO oDTO) throws Exception {
		Map<String,Object> result = new HashMap<>();
		oMapper.insertOrder(oDTO.getOinfo());
		result.put("oinfo",oDTO.getOinfo());
		if(oDTO.getPLists()!=null) oMapper.insertProduct(oDTO.getPLists(), oDTO.getOinfo());
		if(oDTO.getCLists()!=null) {
			if(oMapper.checkClass(oDTO.getOinfo().getMid(), oDTO.getCLists())==0) {
			oMapper.insertClass(oDTO.getCLists(), oDTO.getOinfo());
			oMapper.intoMyClass(oDTO.getCLists(),oDTO.getOinfo());
			}
			else throw new Exception("수강 중인 클래스 존재");
		};
		if(oDTO.isSaveAddr()) {
			mMapper.updateAddress(oDTO.getOinfo());
			mDTO.setMzipcode(oDTO.getOinfo().getOzipcode());
			mDTO.setMphone(oDTO.getOinfo().getPhone());
			mDTO.setMaddress(oDTO.getOinfo().getOaddress1());
			mDTO.setMaddressdetail(oDTO.getOinfo().getOaddress2());
			
		}
		mDTO.setMpoint(mMapper.findByEmail(mDTO.getMid(), mDTO.getSocial()).getMpoint());
		result.put("mDTO",mDTO);
		return result;
	
	}


	/* *Author : 남승현
	 * 기능 : 주문에 필요한 상품 정보를 불러오는 메서드
	 * 매개변수 : 상품 아이디, 상품 수량 
	 * 기타 : 주문 페이지로 넘어올 때, 상품 목록 및 클래스 목록을 불러옴
	 * 		 @Transactional 어노테이션 활용
	 */
	@Transactional
	@Override
	public OrderTotalDTO getInfo(List<String> pids, List<Integer> pamounts) throws Exception {
		List<CinfoDTO> cLists = new ArrayList<>();
		List<PinfoDTO> pLists = new ArrayList<>();
		int i=0;
		for(String pid :pids) {
			if (pid.startsWith("S") || pid.startsWith("O")) {
				try {
					if (cMapper.isExistProduct(pid) == 1) {
						PinfoDTO cDTO =  oMapper.pInfos(pid);
						cDTO.setMcamount(pamounts.get(i));
						int calPrice = (int)(cDTO.getPprice()*(1-cDTO.getPdiscount()/100))*cDTO.getMcamount();
						cDTO.setDiscountPrice(calPrice);
						cDTO.setTotalPrice(cDTO.getPprice()*cDTO.getMcamount());
						pLists.add(cDTO);
					} else {
						throw new Exception("pid가 존재하지 않음");
					}
				} catch (Exception e) {
					throw e;
				}
			}else {
				try {
					if(cMapper.isExistClass(pid)==1) {
						CinfoDTO ciDTO =  oMapper.cInfos(pid);
						ciDTO.setPamount(pamounts.get(i));
						cLists.add(ciDTO);
					}else {
						throw new Exception("존재하지 않는 lcid");
					}
				}catch (Exception e) {
					throw e;
				}
			}
			i++;
		}
			
		return OrderTotalDTO.builder().pLists(pLists).cLists(cLists).build();
	}
	
	
	/* *Author : 남승현
	 * 기능 : 주문 취소 시, 주문서 상태 변경 및 마일리지, 상품 판매량 갱신하는 기능 
	 * 매개변수 : 사용자 아이디, 주문 번호 
	 */
	@Transactional
	@Override
	public int orderCancle(String mid, int oid,int social) throws Exception {
		try {
			oMapper.orderCancle(oid);
			ClubMember2 mDTO = mMapper.findByEmail(mid, social);
			return mDTO.getMpoint();
		}catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	
	/* *Author : 남승현
	 * 기능 : 주문 내역 조회 시, 상품 정보를 불러오는 기능 
	 * 매개변수 : 사용자 아이디, 조회 시작일, 조회 종료일
	 */
	@Override
	public List<SearchResultDTO> searchOrders(SearchDTO sDTO) throws Exception {
		log.info(sDTO);
		List<SearchResultDTO> result = oMapper.getOrders(sDTO);
		result.forEach(item -> item.setOdate(dateFormatter(item.getOdate())));
		return result;
		
	}
	
	public String dateFormatter(String odate) {
		return odate.split(" ")[0].substring(2).replace("-","/");
	}

	/* *Author : 남승현
	 * 기능 : 주문 데이터 중 상세 데이터를 불러오는 기능
	 * 매개변수 : 사용자 아이디, 주문 번호
	 */
	@Override
	public OinfoDTO getOrder(String mid, int oid) throws Exception {
		try {
			return oMapper.getOrder(oid, mid);
		}catch (Exception e) {
			throw e;
		}
	}


	
}
