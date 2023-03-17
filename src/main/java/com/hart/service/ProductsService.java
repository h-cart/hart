package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductsVO;
import com.hart.domain.ProductsVO2;

@Service
public interface ProductsService {

	// (받을거 넣기 가격, 할인율, 할인후 가격, 상품이름)
	
	public List<ProductsVO> getProductList(ProductsVO productsVO) throws Exception;
	
	public List<CategoryVO> getcategorybar() throws Exception;
	
	public List<ProductsVO2> getcategoryproducts(ProductsVO2 products2) throws Exception;
	
	
	public List<ProductsVO> getProductDetail(String pid) throws Exception;
	
	public List<ProductsVO> getproductDetailimg(String pid) throws Exception;
}
