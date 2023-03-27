package com.hart.domain.event;

import java.util.Date;

import lombok.Data;

@Data
public class EventListVO {
	int evid;
	String evtitle;
	Date start_date;
	Date end_date;
	Date vstart_date;
	Date vend_date;
	String crmingredient;

}
