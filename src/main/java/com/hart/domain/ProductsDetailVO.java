package com.hart.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProductsDetailVO {

	
	private List<ProductimgVO> pimg;
	private ProductsVO detail;

	
	public ProductsDetailVO(ProductsVO detail, List<ProductimgVO> pimg) {
		this.detail = detail;
		this.pimg = pimg;
	}
	
};
