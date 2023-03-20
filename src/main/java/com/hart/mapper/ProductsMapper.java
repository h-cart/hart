package com.hart.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductimgVO;
import com.hart.domain.ProductsVO;
import com.hart.domain.RecipeVO;


@Mapper
public interface ProductsMapper {

	public List<ProductsVO> getproductslist(ProductsVO products);
	
	
	public List<CategoryVO> getcategorybar();
	
	//public List<CategoryVO> getcategorysmall(String pid);
	
	public ProductsVO getproductDetail(String pid);
	
	public List<ProductimgVO> getproductDetailimg(String pid);

	public RecipeVO recipelist();
	


	
	
}
