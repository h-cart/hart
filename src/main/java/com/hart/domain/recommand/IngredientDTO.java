package com.hart.domain.recommand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDTO {
		
	private String pid;
	private String pname;
	private String pimg;
	private int pprice;
	private int pdiscount;
	
}
