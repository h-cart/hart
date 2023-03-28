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
public class SearchResultDTO {
	
	private int oid;
	private String odate;
	private int opayment;
	private int ostatus;
	private int ousedpoint;
	private List<OitemDTO> oitems;
}
