package com.hart.domain.product;

import java.sql.Date;
import java.util.List;

import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
	String serving;
	String rreadyingredient;
	int sort;
	int page;
	

	List<CRContentVO> recipeContent;

	public RecipeVO(CRecipeVO recipe) {
		this.rid = "C" + recipe.getCrid();
		this.rtitle = recipe.getCrtitle();
		this.rimg = "/event/api/display?imgName=" + recipe.getCrMimg();
		this.rmingredient = recipe.getCrmingredient();
		this.rlevel = recipe.getCrlevel();
		this.rtime = recipe.getCrtime();
		this.rclick = 0;
		this.rregdate = recipe.getCrregdate();
		this.rcano = recipe.getCrcano();
		this.rdetail = recipe.getCrdetail();
		StringBuilder tmp = new StringBuilder();
		for (CRIngredientVO a : recipe.getCrecipeIngredientVo()) {
			tmp.append(a.getIname()).append(" ").append(a.getCricount()).append("<br>");
		}
		this.rreadyingredient = tmp.toString();
		this.serving = "1인분";
		this.recipeContent = recipe.getCrecipeContent();

	}

};
