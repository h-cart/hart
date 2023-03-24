package com.hart.domain.event;

import lombok.Data;

@Data
public class EventListVO {
	int evid;
	String evtitle;
	Data start_date;
	Data end_date;
	Data vstart_date;
	Data vend_date;

}
