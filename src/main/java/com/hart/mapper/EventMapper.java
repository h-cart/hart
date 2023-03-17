package com.hart.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.ProductsVO;

@Mapper
public interface EventMapper {

	List<ProductsVO> getProducts(String keyword) throws SQLException;

}
