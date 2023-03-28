package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OitemDTO {
	
	private int oid;
	private String pid;
	private String pname;
	private String pimg;
	private int rvcheck;
	private int oamount;
}
