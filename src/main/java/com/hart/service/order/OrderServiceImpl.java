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

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper oMapper;
	
	@Autowired
	private CartMapper cMapper;
	
	@Autowired
	private MemberMapper mMapper;
	
	@Transactional
	@Override
	public Map<String,Object> insertOrder(ClubAuthMemberDTO mDTO, OrderInsertDTO oDTO) throws Exception {
		Map<String,Object> result = new HashMap<>();
		oMapper.insertOrder(oDTO.getOinfo());
		result.put("oinfo",oDTO.getOinfo());
		if(oDTO.getPLists()!=null) oMapper.insertProduct(oDTO.getPLists(), oDTO.getOinfo());
		if(oDTO.getCLists()!=null) {
			if(oMapper.checkClass(oDTO.getOinfo().getMid(), oDTO.getCLists())==0) 
			oMapper.insertClass(oDTO.getCLists(), oDTO.getOinfo());
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
		System.out.println(mDTO);
		return result;
	
	}

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
					log.info(e.getMessage());
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

	@Override
	public OinfoDTO getOrder(String mid, int oid) throws Exception {
		try {
			return oMapper.getOrder(oid, mid);
		}catch (Exception e) {
			log.info(e);
			throw e;
		}
	}


	
}
