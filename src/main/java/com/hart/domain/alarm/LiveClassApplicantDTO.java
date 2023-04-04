package com.hart.domain.alarm;

import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LiveClassApplicantDTO {
	private String mid;
	private String lcname;
	private String lcday;
	private String lcstart;
	private String lcend;
	private String alertPhone;
	
}
