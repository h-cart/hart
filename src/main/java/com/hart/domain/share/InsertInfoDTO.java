package com.hart.domain.share;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertInfoDTO {
	
	private String mid;
	private String mname;
	private String pname;
	private int count;
}
