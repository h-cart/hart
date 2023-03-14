package com.hart.domain.member;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubMember2 implements Serializable {
	private String mid;
	private String mpassword;
	private String mname;
	private int social;
	private String mroles;
}// end class
