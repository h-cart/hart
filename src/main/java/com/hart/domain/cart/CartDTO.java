package com.hart.domain.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDTO {
	
	private List<CProductDTO> pLists;
	private List<CClassDTO> cLists;
	
}
