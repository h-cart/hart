package com.hart.domain.product;

import lombok.Data;

/**
 * @since : 2023. 03. 01.
 * @FileName: ProductsCategorylistVO
 * @author : 박정훈
 * @설명 : 대분류 카테고리 
 */
@Data
public class ProductCategorylistVO {
	int pcno;
	String pcategory;
	int pcno_top;

}
