package com.hart.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductsVO;
import com.hart.domain.ProductsVO2;

@Mapper
public interface ProductsMapper {

	public List<ProductsVO> getproductslist(ProductsVO products);
	
	public List<CategoryVO> getcategorybar();
	
	public List<ProductsVO2> getcategoryproducts(ProductsVO2 products2);
	
	
}
