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
public class RecommandDTO {
	
	private String pid;
	private String pname;
	private String pimg;
	private List<RrecipeDTO> recipes;
	
}
