package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.ProductsVO;

@Service
public interface ProductsService {

	// (받을거 넣기 가격, 할인율, 할인후 가격, 상품이름)
	
	public List<ProductsVO> getProductList(ProductsVO productsVO) throws Exception;
}
