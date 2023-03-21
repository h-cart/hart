package com.hart.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CClassDTO {
	
	private String lcid;
	private String lcday;
	private int lcstatus;
	private String lcteacher;
	private String lcname;
	private String lcimg;
	private int lcprice;
}
