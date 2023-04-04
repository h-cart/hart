package com.hart.domain.admin;

import java.util.Date;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum;
	private int amount;
	private int evid;

	public Criteria() {
		this(1, 10, 0);
	}

	public Criteria(int pageNum, int amount, int evid) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.evid = evid;

	}

}
