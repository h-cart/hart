package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.CategoryVO;
import com.hart.domain.FillterVO;
import com.hart.domain.ProductCategorylistVO;
import com.hart.domain.ProductsDetailVO;
import com.hart.domain.ProductsVO;

@Service
public interface ProductsService {

	// (받을거 넣기 가격, 할인율, 할인후 가격, 상품이름)
	
	public List<ProductsVO> getproductslist(int pcno) throws Exception;
	
	public List<CategoryVO> getcategorybar() throws Exception;
	
	
	//public List<CategoryVO> getcategorysmall(String pid) throws Exception;
	
	public ProductsDetailVO getProductDetails(String pid) throws Exception;

	
	//List<CategoryVO> getcategorysmall(int pcno);

	//List<CategoryVO> getcategorysmall(String pcno);
	
	public List<ProductCategorylistVO> getproductcatrogrtlist(int pcno) throws Exception;

	
	public List<ProductsVO> getproductslistajax(int pcno) throws Exception;

//	public List<ProductlistintegVO> Productlistinteg(int pcno) throws Exception;

	
	public List<ProductsVO> fillter(FillterVO fVO) throws Exception;
	
	
}
