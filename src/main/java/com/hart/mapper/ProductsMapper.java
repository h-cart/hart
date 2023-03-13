package com.hart.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hart.domain.ProductsVO;

@Mapper
public interface ProductsMapper {

	public List<ProductsVO> getproductslist(ProductsVO products);
}
