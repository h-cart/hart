package com.hart.domain.product;

import java.sql.Date;

import lombok.Data;

@Data
public class RecipeVO {
	String rid;
	String rtitle;
	String rimg;
	String rmingredient;
	String rlevel;
	String rtime;
	int rclick;
	Date rregdate;
	int rcano;
	String rdetail;

	String rcimg;
	String rcstep;


};
