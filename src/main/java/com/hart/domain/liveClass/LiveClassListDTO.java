package com.hart.domain.liveClass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LiveClassListDTO {
	private String lcid;
	private String lcday;
	private String lcstart;
	private String lcend;
	private int lcstatus;
	private String lcteacher;
	private String lcplace;
	private String lcexplain;
	private String lcstudent;
	private String lckeyword;
	private String lcname;
	private int lcprice;
	private int lccount;
	private String lctexplain;
	private String lcimg;
	private String lcdate;
	private String lcingredient;
	private String lcdayofweek;
}
