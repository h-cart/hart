package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OinfoDTO {
	private String oid;
	private String oname;
	private int ozipcode;
	private String oaddress1;
	private String oaddress2;
	private int opayment;
	private int ousedpoint;
	private int odiscount;
	private String phone;
	private String mid;
	
	
}
