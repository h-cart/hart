package com.hart.domain.order;

import java.util.List;

import com.hart.domain.cart.CProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTotalDTO {
	
	private List<CinfoDTO> cLists;
	private List<PinfoDTO> pLists;
	
}
