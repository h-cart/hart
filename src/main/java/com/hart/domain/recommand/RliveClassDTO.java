package com.hart.domain.recommand;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RliveClassDTO {
	
	private String lcid;
	private int lcstatus;
	private String lcname;
	private String lcteacher;
	private int lcprice;
	private String lcimg;
	private List<IngredientDTO> items;
}
