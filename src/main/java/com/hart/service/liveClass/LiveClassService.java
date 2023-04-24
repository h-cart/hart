package com.hart.service.liveClass;

import java.sql.SQLException;
import java.util.List;

import com.hart.domain.admin.AdminLiveClassDTO;
import com.hart.domain.liveClass.LiveClassDetailInfoDTO;
import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.LiveClassRegisterDTO;
import com.hart.domain.liveClass.LiveClassVideoDTO;
import com.hart.domain.liveClass.MyLiveClassInfoDTO;

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
public interface LiveClassService {
	
	//liveClass 목록 불러오기
	public List<LiveClassListDTO> getList() throws SQLException;
	//liveClass 상세 불러오기
	public LiveClassDetailInfoDTO getClassDetail(String lcid) throws SQLException;
	//liveClass 상세 수강확인
	public int checkClassMember(String lcid,String mid) throws SQLException;
	//myPage liveClass 목록 불러오기
	public List<MyLiveClassInfoDTO> getMyClassInfo(String mid) throws SQLException;
	//liveClass video 불러오기
	public LiveClassVideoDTO getClassVideo(String lcid) throws SQLException;
	//adminliveClass 목록 불러오는 서비스 
	public List<AdminLiveClassDTO> getClassAdminList() throws SQLException;
	//liveClassVod 등록
	public void registerVOD(LiveClassRegisterDTO dto) throws SQLException;
	
}
