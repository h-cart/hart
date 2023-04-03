package com.hart.domain.liveClass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LiveClassVideoDTO {
	private String lcteacher;
	private String lcexplain;
	private String lcingredient;
	private String lcname;
	private String vpath;
	private String[] ingredientList;
	//private String[] lctExplainList;
}
