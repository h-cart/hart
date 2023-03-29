package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.ProductCategorylistVO;
import com.hart.domain.product.ProductsDetailVO;
import com.hart.domain.product.ProductsVO;

@Service
public interface ProductsService {

	// (받을거 넣기 가격, 할인율, 할인후 가격, 상품이름)
	
	public List<ProductsVO> getproductslist(int pcno) throws Exception;
	
	public List<CategoryVO> getcategorybar() throws Exception;
	
	
	//public List<CategoryVO> getcategorysmall(String pid) throws Exception;
	
	public ProductsDetailVO getProductDetails(String pid) throws Exception;

	
	//List<CategoryVO> getcategorysmall(int pcno);

	//List<CategoryVO> getcategorysmall(String pcno);
	
	public List<ListVO> getproductcatrogrtlist(int pcno) throws Exception;

	
	//public List<ProductsVO> getproductslistajax(int pcno,int page) throws Exception;

//	public List<ProductlistintegVO> Productlistinteg(int pcno) throws Exception;
	

	//public List<ListVO> List(ListVO ListVO) throws Exception;

	public List<ListVO> Productlist(ListVO list);



	//List<ProductsVO> getproductslistajax(int pcno) throws Exception;
	
	
}
