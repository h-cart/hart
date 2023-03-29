package com.hart.domain.admin;


import java.util.Date;

import lombok.Data;

@Data
public class AdminEventVO {
	int evid;
	String crid;
	String mid;
	String crtitle;
	Date crregdate;	
	
}
