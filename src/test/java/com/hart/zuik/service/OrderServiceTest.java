package com.hart.zuik.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.OrderInsertDTO;
import com.hart.domain.order.SearchDTO;
import com.hart.service.order.OrderService;

import lombok.extern.log4j.Log4j2;
@SpringBootTest
@Log4j2
public class OrderServiceTest {
	
	@Autowired
	private OrderService oService;
	
	@Test
	public void getP() {
		List<String> list = new ArrayList<>();
		list.add("S02209125009");
		list.add("S02105049300");
		list.add("S02006004340");
		list.add("S02006004339");
		
	}
	@Test
	public void insertOrder() throws Exception{
		OinfoDTO oDTO = OinfoDTO.builder()
				.mid("skarns23@gmail.com")
				.ozipcode(13524)
				.oaddress1("경복")
				.oaddress2("707")
				.opayment(80000)
				.odiscount(0)
				.oname("쥑")
				.build();
//		oService.insertOrder(OrderInsertDTO.builder()
//							.oinfo(oDTO).build());
	}
	
	
	@Test
	public void SearchOrder() throws Exception{
		log.info(oService.searchOrders(SearchDTO.builder()
				.sdate("23/03/26")
				.edate("23/03/28")
				.mid("skarns23@gmail.com")
				.build()
				));
	}
}
