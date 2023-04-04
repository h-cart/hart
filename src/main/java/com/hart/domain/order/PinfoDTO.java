package com.hart.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PinfoDTO {

	private String pid;
	private String pimg;
	private String pname;
	private int pprice;
	private int pdiscount;
	private int mcamount;
	private int totalPrice;
	private int discountPrice;
}
