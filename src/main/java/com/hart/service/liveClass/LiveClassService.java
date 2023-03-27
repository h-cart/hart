package com.hart.service.liveClass;

import java.util.List;

import com.hart.domain.liveClass.LiveClassListDTO;
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

	public List<LiveClassListDTO> getList();
	public LiveClassListDTO getClassDetail(String lcid);
	public List<MyLiveClassInfoDTO> getMyClassInfo(String mid);
	
	
}
