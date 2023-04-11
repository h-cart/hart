package com.hart.service.liveClass;

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
 * @설명 : 
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강      
 *     </pre>
 */
public interface LiveClassService {
	
	//liveClass 목록 불러오기
	public List<LiveClassListDTO> getList();
	//liveClass 상세 불러오기
	public LiveClassDetailInfoDTO getClassDetail(String lcid);
	//liveClass 상세 수강확인
	public int checkClassMember(String lcid,String mid);
	
	//myPage liveClass 목록 불러오기
	public List<MyLiveClassInfoDTO> getMyClassInfo(String mid);
	//liveClass video 불러오기
	public LiveClassVideoDTO getClassVideo(String lcid);
	
	//adminliveClass 목록 불러오는 서비스 
	public List<AdminLiveClassDTO> getClassAdminList();
	
	//liveClassVod 등록
	public void registerVOD(LiveClassRegisterDTO dto);
	
	
	
	
	
	
}
