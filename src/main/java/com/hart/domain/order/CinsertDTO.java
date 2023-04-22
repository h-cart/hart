package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @since : 2023. 3. 20.
 * @FileName: CinsertDTO.java
 * @author : 남승현
 * @설명 : 주문 시, 삽입에 필요한 클래스 데이터를 모아놓은 DTO
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 3. 20.     남승현       CinsertDTO 구현
 *     </pre>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinsertDTO {
	
	String lcid;
	String pamount;
}
