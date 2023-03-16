package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.liveClass.LiveClassListDTO;

/**
 * @since : 2023. 3. 15.
 * @FileName: LiveClassMapper.java
 * @author : 함세강
 * @설명 : 데이터베이스 mybatis Interface
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 15.     함세강           LiveClassMapper
 *     </pre>
 */

@Mapper
public interface LiveClassMapper {
	//라이브 클래스 목록 불러오기
	public List<LiveClassListDTO> getLiveList();
	//라이브 클래스 상세 불러오기
	public LiveClassListDTO getDetail(@Param("lcid") String lcid);
	
}
