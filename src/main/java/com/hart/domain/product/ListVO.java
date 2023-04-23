package com.hart.domain.product;

import lombok.Data;

/**
 * @since : 2023. 03. 1.
 * @FileName: ListVO
 * @author : 박정훈
 * @설명 : 상품 목록 페이지VO
 */
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
	String pcat;
	int totalcount;
	String keyword;
	
	


};
