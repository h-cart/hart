package com.hart.domain.alarm;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDTO {
	
	private List<AlarmDTO> list;
	private String alarmCount;
	
}
