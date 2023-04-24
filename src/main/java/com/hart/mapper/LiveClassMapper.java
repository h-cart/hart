package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.admin.AdminLiveClassDTO;
import com.hart.domain.liveClass.LiveClassDetailDTO;
import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.LiveClassRegisterDTO;
import com.hart.domain.liveClass.LiveClassVideoDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;

/**
 * @since : 2023. 3. 15.
 * @FileName: LiveClassMapper.java
 * @author : 함세강
 * @설명 : 데이터베이스 mybatis Interface
 * 
 *<pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강          getLiveList mapper 구현
 * 2023. 3. 17.     함세강      	getLiveClassDetail mapper 구현
 * 2023. 3. 20.     함세강      	getLiveClassDetailInfo mapper 구현
 * 2023. 3. 21.     함세강      	checkClassMember mapper 구현
 * 2023. 3. 25.     함세강      	getMyLiveClassInfo mapper 구현
 * 2023. 3. 27.     함세강      	getMyVideo mapper 구현
 * 2023. 3. 27.     함세강      	getAdminLiveClass mapper 구현
 * 2023. 3. 27.     함세강      	registerLiveClassVOD mapper 구현
 *</pre>
 */

@Mapper
public interface LiveClassMapper {
	//라이브 클래스 목록 불러오기
	public List<LiveClassListDTO> getLiveList() throws SQLException;
	//라이브 클래스 상세 불러오기
	public LiveClassListDTO getLiveClassDetail(@Param("lcid") String lcid) throws SQLException;
	//라이브 클래스 상세 설명 불러오기
	public LiveClassDetailDTO getLiveClassDetailInfo(@Param("lcid") String lcid) throws SQLException;
	//라이브 클래스 상세 수강내역 확인
	public int checkClassMember(@Param("lcid") String lcid,@Param("mid") String mid) throws SQLException;
	//마이페이지 수강신청한 라이브 클래스 불러오기
	public List<MyLiveClassInfoDTO> getMyLiveClassInfo(@Param("mid") String mid) throws SQLException;
	//수강 신청한 라이브 클래스 비디오 불러오기
	public LiveClassVideoDTO getMyVideo(@Param("lcid") String lcid) throws SQLException;
	//라이브 클래스 관리자 목록 불러오기
	public List<AdminLiveClassDTO> getAdminLiveClass() throws SQLException;
	//라이브 클래스 VOD 등록
	public int registerLiveClassVOD(LiveClassRegisterDTO dto) throws SQLException;
	
	
	
}
