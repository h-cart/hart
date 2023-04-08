package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDTO {
	
	private String mid;
	private String sdate;
	private String edate;
	private int isCancel;
}
