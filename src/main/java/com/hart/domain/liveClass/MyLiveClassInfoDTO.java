package com.hart.domain.liveClass;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MyLiveClassInfoDTO {
	private String lcid;
	private String lcday;
	private String lcstart;
	private String lcend;
	private int lcstatus;
	private String lcname;
	private String lcimg;
	private String lcdate;
	private String wholeDate;
}
