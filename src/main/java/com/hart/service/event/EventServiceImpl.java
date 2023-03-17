package com.hart.service.event;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.ProductsVO;
import com.hart.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final EventMapper mapper;

	@Override
	public List<ProductsVO> getList(String keyword) throws SQLException {
		return mapper.getProducts(keyword);
	}

}
