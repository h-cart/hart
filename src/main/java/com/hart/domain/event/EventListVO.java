package com.hart.domain.event;

import java.util.Date;

import lombok.Data;

@Data
public class EventListVO {
	int evid;
	String evtitle;
	String start_date;
	String end_date;
	String vstart_date;
	String vend_date;
	String crmingredient;

}
