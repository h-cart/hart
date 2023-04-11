package com.hart.service.liveClass;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.admin.AdminLiveClassDTO;
import com.hart.domain.liveClass.LiveClassDetailInfoDTO;
import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.LiveClassRegisterDTO;
import com.hart.domain.liveClass.LiveClassVideoDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;
import com.hart.mapper.LiveClassMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LiveClassServiceImpl implements LiveClassService{

	private final LiveClassMapper mapper;
	
	@Override
	public List<LiveClassListDTO> getList() {
		log.info("getList 서비스 호출");
		List<LiveClassListDTO> list= new ArrayList<>();
		try {
			list = mapper.getLiveList();
			log.info(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public LiveClassDetailInfoDTO getClassDetail(String lcid) {
		log.info("getClassDetail 서비스 호출");
		LiveClassListDTO dto =  new LiveClassListDTO();
		try {
			dto = mapper.getLiveClassDetail(lcid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(dto);
		LiveClassDetailInfoDTO infoDTO = new LiveClassDetailInfoDTO();
		
		StringBuilder sb = new StringBuilder();
		sb.append(dto.getLcdate()+" ("+dto.getLcdayofweek()+") "+dto.getLcstart()+"-"+dto.getLcend());
		infoDTO.setWholeDate(sb.toString());
		infoDTO.setIngredientList(dto.getLcingredient().replaceAll(", ","@").split("@"));
		infoDTO.setLctExplainList(dto.getLctexplain().substring(1).split("-"));
		infoDTO.setLcStudentList(dto.getLcstudent().substring(1).split("-"));
		
		//infoDTO.setLcStudentList(dto.getLcstudent().replaceAll(" - ", "@").substring(1).split("@"));
		//infoDTO.setLctExplainList(dto.getLctexplain().split("-"));
		//infoDTO.setLcStudentList(dto.getLcstudent().split(" - "));
		infoDTO.setLiveClassListDTO(dto);
		
		log.info(infoDTO);
		
		return infoDTO;
	}

	@Override
	public List<MyLiveClassInfoDTO> getMyClassInfo(String mid) {
		log.info("getMyClassInfo 서비스 호출");
		List<MyLiveClassInfoDTO> list =  new ArrayList<>();
		try {
			list = mapper.getMyLiveClassInfo(mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Calendar startTime = Calendar.getInstance(); 
		Calendar endTime = Calendar.getInstance(); 
		long now = System.currentTimeMillis();
		
		for(MyLiveClassInfoDTO dto : list) {
			StringBuilder sb = new StringBuilder();
			sb.append(dto.getLcdate()+" ("+dto.getLcdayofweek()+") "+dto.getLcstart()+"-"+dto.getLcend());
			dto.setWholeDate(sb.toString());
			
			startTime.set(Calendar.YEAR, Integer.parseInt(dto.getLcday().substring(0, 4)));
			startTime.set(Calendar.MONTH, Integer.parseInt(dto.getLcday().substring(5, 7))-1);
			startTime.set(Calendar.DATE, Integer.parseInt(dto.getLcday().substring(8,10)));
			startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dto.getLcstart().substring(0, 2)));
			startTime.set(Calendar.MINUTE, Integer.parseInt(dto.getLcstart().substring(3)));
			startTime.set(Calendar.SECOND, 0);
			
			endTime.set(Calendar.YEAR, Integer.parseInt(dto.getLcday().substring(0, 4)));
			endTime.set(Calendar.MONTH, Integer.parseInt(dto.getLcday().substring(5, 7))-1);
			endTime.set(Calendar.DATE, Integer.parseInt(dto.getLcday().substring(8,10)));
			endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dto.getLcend().substring(0, 2)));
			endTime.set(Calendar.MINUTE, Integer.parseInt(dto.getLcend().substring(3)));
			endTime.set(Calendar.SECOND, 0);
			
			long startDateMillis = startTime.getTimeInMillis();
			long endDateMillis = endTime.getTimeInMillis();
			if(startDateMillis<=now && now<=endDateMillis) {
				dto.setLcstatus(1);//방송 중
			} else if(endDateMillis<now) {
				dto.setLcstatus(2);//지난 방송
			}
			
		}
		log.info(list);
		
		return list;
		
	}

	@Override
	public LiveClassVideoDTO getClassVideo(String lcid) {
		log.info("getClassVideo 서비스 호출");
		LiveClassVideoDTO dto = new LiveClassVideoDTO();
		try {
			dto = mapper.getMyVideo(lcid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dto.setIngredientList(dto.getLcingredient().replaceAll(", ","@").split("@"));
		return dto;
	}

	@Override
	public List<AdminLiveClassDTO> getClassAdminList() {
		List<AdminLiveClassDTO> list = new ArrayList<>();
		try {
			list = mapper.getAdminLiveClass();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void registerVOD(LiveClassRegisterDTO dto) {
		String path = dto.getClassVodpath();
		int result=0;
		dto.setClassVodpath("https://dg15tp5w47tfz.cloudfront.net/"+path);
		try {
			result = mapper.registerLiveClassVOD(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(result);
	}

	@Override
	public int checkClassMember(String lcid, String mid) {
		int memberCheck=0;
		try {
			memberCheck = mapper.checkClassMember(lcid, mid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberCheck;
	}

}
