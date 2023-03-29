package com.hart.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.admin.AdminEventVO;
import com.hart.mapper.AdminEventMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	private AdminEventMapper mapper;

	@Override
	public List<AdminEventVO> getList(AdminEventVO event) {

		return mapper.getEventList(event);
	}

}
