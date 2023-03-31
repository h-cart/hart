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
	private String pid;
	private String ptitle;
	private String level;
	private String rtime;
	private List<IngredientDTO> items;
	
}
