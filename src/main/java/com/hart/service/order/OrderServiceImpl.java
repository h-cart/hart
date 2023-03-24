package com.hart.service.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hart.domain.cart.CProductDTO;
import com.hart.domain.order.CinfoDTO;
import com.hart.domain.order.OrderTotalDTO;
import com.hart.domain.order.PinfoDTO;
import com.hart.mapper.CartMapper;
import com.hart.mapper.OrderMapper;

import lombok.extern.log4j.Log4j2;

@org.springframework.stereotype.Service
@Log4j2
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper oMapper;
	
	@Autowired
	private CartMapper cMapper;
	
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
	


}
