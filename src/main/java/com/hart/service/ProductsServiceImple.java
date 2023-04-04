package com.hart.service;





import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.ProductimgVO;
import com.hart.domain.product.ProductsDetailVO;
import com.hart.domain.product.ProductsVO;
import com.hart.mapper.ProductsMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductsServiceImple implements ProductsService {

	@Autowired
	private ProductsMapper productsmapper;

	@Override
	public List<ProductsVO> getproductslist(int pcno) {

		//log.info("#############productVO getProductListImple @@@@@@@@@=" + pcno);
		// 1제품 목록 가져오기
		return productsmapper.getproductslist(pcno);
	}

	@Override
	public List<CategoryVO> getcategorybar() {
		// 2대분류 카테고리 가져오기
		return productsmapper.getcategorybar();
	}

	@Override
	public ProductsDetailVO getProductDetails(String pid) {
		// 3 제품 상페 이미지 가져오기
		ProductsVO detail = productsmapper.getproductDetail(pid);
		List<ProductimgVO> pimg = productsmapper.getproductDetailimg(pid);
		// 4 제품 상세 이미지 가져오기
		return new ProductsDetailVO(detail, pimg);

	}

	
	
	@Override
	public List<ListVO> getproductcatrogrtlist(int pcno) throws Exception {
		//log.info("===============getproductcatrogrtlist  ServiceImple ============>" + pcno);
		
		
		return productsmapper.getproductcatrogrtlist(pcno);
	}

	
	

	//에이작스 및 소 카테고리 및 필터적용 Mapper 
	@Override
	public List<ListVO> Productlist(ListVO list) {
		 log.info("===============getproductcatrogrtlist Service ServiceImple ============>" + list);
		 log.info("===============getproductcatrogrtlist Service ServiceImple ============>" + productsmapper.Productlist(list));
		
		
		 
		return productsmapper.Productlist(list);
	}

	



	


}
