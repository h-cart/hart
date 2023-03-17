package com.hart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductimgVO;
import com.hart.domain.ProductsVO;
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
	
	
	/*
	 * @Override public <ProductsVO> getProductDetail(String pid){
	 * 
	 * <ProductsVO> detail=productsmapper.getproductDetail(pid); List<ProductimgVO>
	 * pimg =productsmapper.getproductDetailimg(pid);
	 * 
	 * return ; }
	 */

	
	
	
	
	
}
