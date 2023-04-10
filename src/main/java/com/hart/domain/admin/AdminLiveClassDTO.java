package com.hart.domain.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminLiveClassDTO {
	
	private String lcid;
	private String lcname;
	private String lcdate;
	private String lcstart;
	private String lcend;
	private String lcteacher;
	private String lckeyword;
	private int classreg;
	
}
