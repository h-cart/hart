package com.hart.domain.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInsertDTO {
		
	private OinfoDTO oinfo;
	private List<PinsertDTO> pLists;
	private List<CinsertDTO> cLists;
	private boolean saveAddr;
	
}
