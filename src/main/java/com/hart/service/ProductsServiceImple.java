package com.hart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductsVO;
import com.hart.domain.ProductsVO2;
import com.hart.mapper.ProductsMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductsServiceImple implements ProductsService {
	
	@Autowired
	private ProductsMapper productsmapper;

	@Override
	public List<ProductsVO> getProductList(ProductsVO productsVO) {
		
		log.info("#############productVO @@@@@@@@@=" +productsVO );
		
		return productsmapper.getproductslist(productsVO);
	}
	
	
	
	
	@Override
	public List<CategoryVO> getcategorybar(){
		
		return productsmapper.getcategorybar();
	}
	
	
	
	@Override
	public List<ProductsVO2> getcategoryproducts(ProductsVO2 products2){
		
		return productsmapper.getcategoryproducts(products2);
	}
	
	
	@Override
	public List<ProductsVO> getProductDetail(String pid){
		
		
		return productsmapper.getproductDetail(pid);
	}
	
	
	@Override
	public List<ProductsVO> getproductDetailimg(String pid){
		
		
		return productsmapper.getproductDetailimg(pid);
	}
	
	
}
