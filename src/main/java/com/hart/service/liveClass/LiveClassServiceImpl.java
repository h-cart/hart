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

/**
 * @since : 2023. 3. 15.
 * @FileName: LiveClassService.java
 * @author : 함세강
 * @설명 : LiveClassService 구현
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강          getList 서비스 구현
 * 2023. 3. 17.     함세강      	getClassDetail 서비스 구현
 * 2023. 3. 20.     함세강      	checkClassMember 서비스 구현
 * 2023. 3. 21.     함세강      	getMyClassInfo 서비스 구현
 * 2023. 3. 25.     함세강      	getClassVideo 서비스 구현
 * 2023. 3. 27.     함세강      	getClassAdminList 서비스 구현
 * 2023. 3. 27.     함세강      	registerVOD 서비스 구현
 *     </pre>
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class LiveClassServiceImpl implements LiveClassService{

	private final LiveClassMapper mapper;
	
	@Override
	public List<LiveClassListDTO> getList() throws SQLException {
		log.info("getList 서비스 호출");
		List<LiveClassListDTO> list= new ArrayList<>();
		try {
			list = mapper.getLiveList();
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		return list;
	}

	@Override
	public LiveClassDetailInfoDTO getClassDetail(String lcid) throws SQLException {
		log.info("getClassDetail 서비스 호출");
		LiveClassListDTO dto =  new LiveClassListDTO();
		try {
			dto = mapper.getLiveClassDetail(lcid);
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		LiveClassDetailInfoDTO infoDTO = new LiveClassDetailInfoDTO();
		
		StringBuilder sb = new StringBuilder();
		//데이터를 표현하고 싶은 방식으로 가공하는 과정
		sb.append(dto.getLcdate()+" ("+dto.getLcdayofweek()+") "+dto.getLcstart()+"-"+dto.getLcend());
		infoDTO.setWholeDate(sb.toString());
		infoDTO.setIngredientList(dto.getLcingredient().replaceAll(", ","@").split("@"));
		infoDTO.setLctExplainList(dto.getLctexplain().substring(1).split("-"));
		infoDTO.setLcStudentList(dto.getLcstudent().substring(1).split("-"));
		infoDTO.setLiveClassListDTO(dto);
		
		log.info(infoDTO);
		
		return infoDTO;
	}

	@Override
	public List<MyLiveClassInfoDTO> getMyClassInfo(String mid) throws SQLException {
		log.info("getMyClassInfo 서비스 호출");
		List<MyLiveClassInfoDTO> list =  new ArrayList<>();
		try {
			list = mapper.getMyLiveClassInfo(mid);
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		Calendar startTime = Calendar.getInstance(); 
		Calendar endTime = Calendar.getInstance(); 
		long now = System.currentTimeMillis();
		
		//지난강의, 강의예정, 방송 중을 설정해 주는 로직
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
				dto.setLcstatus(2);//지난강의
			}
			
		}
		
		return list;
		
	}

	@Override
	public LiveClassVideoDTO getClassVideo(String lcid) throws SQLException {
		log.info("getClassVideo 서비스 호출");
		LiveClassVideoDTO dto = new LiveClassVideoDTO();
		try {
			dto = mapper.getMyVideo(lcid);
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		dto.setIngredientList(dto.getLcingredient().replaceAll(", ","@").split("@"));
		return dto;
	}

	@Override
	public List<AdminLiveClassDTO> getClassAdminList() throws SQLException {
		List<AdminLiveClassDTO> list = new ArrayList<>();
		try {
			list = mapper.getAdminLiveClass();//라이브클래스 관리자 페이지 목록 정보 불러오기
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		return list;
	}

	@Override
	public void registerVOD(LiveClassRegisterDTO dto) throws SQLException {
		String path = dto.getClassVodpath();
		int result=0;
		//aws cloudfront로 배포된 도메인과 s3저장 경로를 이용해 vod를 등록하는 과정
		dto.setClassVodpath("https://dg15tp5w47tfz.cloudfront.net/"+path);
		try {
			result = mapper.registerLiveClassVOD(dto);
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		log.info(result);
	}

	@Override
	public int checkClassMember(String lcid, String mid) throws SQLException {
		int memberCheck=0;
		try {
			//수강 내역이 있는 정보는 확인하는 과정
			memberCheck = mapper.checkClassMember(lcid, mid);
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw e;
		}
		return memberCheck;
	}

}
