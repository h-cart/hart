package com.hart.zuik.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hart.domain.order.OinfoDTO;
import com.hart.domain.order.SearchDTO;
import com.hart.mapper.OrderMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class OrderMapperTests {

	@Autowired(required = false)
	private OrderMapper oMapper;

	@Test
	public void getProducts() throws Exception{
		List<String> list = new ArrayList<>();
		log.info(oMapper);
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
		oMapper.insertOrder(oDTO);
		
	}
	
	@Test
	public void getOrders() throws Exception{
		log.info(oMapper);
		SearchDTO sDTO = SearchDTO.builder()
						.sdate("23/03/26")
						.edate("23/03/28")
						.mid("skarns23@gmail.com")
						.build();
		oMapper.getOrders(sDTO).forEach(item -> log.info(item));
	}

}
