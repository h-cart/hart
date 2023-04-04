package com.hart.domain.event;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class CRecipeVO {
	String crid;
	String crtitle;
	String crMimg;
	String crmingredient;
	String crlevel;
	String crtime;
	int crclick;
	Date crregdate;
	int crcano;
	String crdetail;
	String mid;
	int evid;
	
	List<CRContentVO> crecipeContent;
	List<CRIngredientVO> crecipeIngredientVo;

}
