package com.hart.domain.product;

import lombok.Data;

@Data
public class ProductsVO {
	String pid;

	String porigin;
	int pcount;
	String pdetail;
	String pbname;
	String pbsmall;
	String pbdesc;
	String pname;
	int pprice;
	int pcno;
	String stag;
	int pdiscount;
	String ptitle;
	String pcategory;
	String pimg;
	int pdepth;
	int page;
	int end;
	
	/*---
	 * pd.pname,pd.pprice,pd.pcno,pd.stag,pd.pdiscount,pd.pid,
	 * primg.pimg,primg.pdepth
	 *---
	 * where pc.pcno_top= #{pcno} ,where pdepth = 0 //대 카테고리 눌럿을때 상품 불러옴 
	 * products p join product_category pc
	 * ---
	 * where pcno=#{pcno} and pdepth=0 //소 카테고리 눌럿을때 상품 가져옴 
	 * products pd join product_img primg
	 * where a.rownumber >  9 * ${page}  
	 * and a.rownumber <=   9* ${page} + 9  	
	 * ---
	 * where pcno_top=#{pcno} // 왼쪽 카테고리 목록이름 불러옴 
	 * where pc.pcno_top= #{pcno_top} 카테고리 필터
	 * 
	 * --
	 * 가격순 카테고리 필터 
	 * where pc.pcno_top= #{pcno_top}
	 *  products p join product_category
	 * <where>
		pdepth = 0
		<if test="pcno != 0">
				AND pcno = #{pcno}
		</if>
		</where>
		<trim prefix="ORDER BY">
			<if test='type == "price"'> pprice</if>
			<if test='type == "sales"'> pcount </if>
			<if test="sort == 1 "> DESC </if>
			<if test="sort == 2 "> ASC</if>
		</trim>
	 * --
	 *
	 */
};
