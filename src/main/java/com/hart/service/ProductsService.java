package com.hart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.MainProdVO;
import com.hart.domain.product.ProductsDetailVO;
import com.hart.domain.product.ProductsVO;



/**
 * @since : 2023. 03.28.
 * @FileName: ProductsService.java
 * @author : 박정훈
 * @설명 : 상품 리스트 및 상세 서비스
 * 
 */
@Service
public interface ProductsService {

	
	public List<ProductsVO> getproductslist(int pcno) throws Exception;
	
	public List<CategoryVO> getcategorybar() throws Exception;
	
	public ProductsDetailVO getProductDetails(String pid) throws Exception;
	
	public List<ListVO> getproductcatrogrtlist(int pcno) throws Exception; 

	public List<ListVO> Productlist(ListVO list);

	public List<CategoryVO> getcategorybar(int pcno);
	
	public List<MainProdVO> getmainprod();
	
}

