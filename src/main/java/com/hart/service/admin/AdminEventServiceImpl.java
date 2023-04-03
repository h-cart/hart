package com.hart.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
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
	public void voteList(AdminEventVO event) {
		mapper.voteList(event);

	}

	@Override
	public List<AdminEventVO> getList(Criteria cri) {
		return mapper.getEventList(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<AdminEventVO> getEventList() {

		return mapper.getEventCateList();
	}

	@Override
	public List<AdminEventVO> getVoteList(Criteria cri) {
		return mapper.getVoteList(cri);
	}

}
