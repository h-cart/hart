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
public class RrecipeDTO {
	private String rid;
	private String rtitle;
	private String rlevel;
	private String rtime;
	private String rimg;
	private String rcano;
	private String rccategory;
	private List<IngredientDTO> items;
	
}
