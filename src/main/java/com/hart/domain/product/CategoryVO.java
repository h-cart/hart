package com.hart.domain.product;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @since : 2023. 03. 4.
 * @FileName: CategoryVO
 * @author : 박정훈
 * @설명 : 상품 목록 페이지에 카테고리 출력
 */
@Data
@Getter
@Setter
public class CategoryVO {
	String pcategory;
	int pcno;
	int pcno_top;
	
}
