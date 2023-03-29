package com.hart.domain.product;

import lombok.Data;

@Data

public class ListVO {

	String pid;
	int pcount;
	String pname;
	int pprice;
	int pcno;
	int pcno_top;
	int pdiscount;
	String pcategory;
	String pimg;
	int page;
	 String type;
	int sort;

	/*
	 * public ListVO(){ this(0,0); }
	 * 
	 * public ListVO(int page,int pcno_top){
	 * 
	 * this.page=page; this.pcno_top=pcno_top; }
	 */

};
