package com.hart.domain.admin;

import java.util.Date;

import lombok.Data;

@Data
public class AdminEventVO {
	int evid;
	String evtitle;
	String crid;
	String mid;
	String crtitle;
	Date crregdate;
	int crvote;
	int count;

}
