package com.hart.domain.order;

import com.hart.domain.cart.CClassDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CinfoDTO {

	private String lcid;
	private String lcday;
	private int lcstatus;
	private String lcteacher;
	private String lcname;
	private String lcimg;
	private int lcprice;
	private int pamount;

}
