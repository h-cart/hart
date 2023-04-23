package com.hart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.MainProdVO;
import com.hart.domain.product.ProductimgVO;
import com.hart.domain.product.ProductsDetailVO;
import com.hart.domain.product.ProductsVO;
import com.hart.mapper.ProductsMapper;

import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 03.28.
 * @FileName: ProductsServiceImple.java
 * @author : 박정훈
 * @설명 : 상품 리스트 및 상세 서비스 임플리먼트
 * 
 */
@Log4j2
@Service
public class ProductsServiceImple implements ProductsService {

	@Autowired
	private ProductsMapper productsmapper;

	@Override
	public List<ProductsVO> getproductslist(int pcno) {
		return productsmapper.getproductslist(pcno);
	}

	@Override
	public List<CategoryVO> getcategorybar() {
		return productsmapper.getcategorybar();
	}
	
	//상단 카테고리 가져오기
	@Override
	public List<CategoryVO> getcategorybar(int pcno) {
		return productsmapper.getsmallcategorybar(pcno);
	}

	// 상품디테일의 이미지와 설명 가져오기
	@Override
	public ProductsDetailVO getProductDetails(String pid) {
		ProductsVO detail = productsmapper.getproductDetail(pid);
		List<ProductimgVO> pimg = productsmapper.getproductDetailimg(pid);
		return new ProductsDetailVO(detail, pimg);
	}

	@Override
	public List<ListVO> getproductcatrogrtlist(int pcno) throws Exception {

		return productsmapper.getproductcatrogrtlist(pcno);
	}

	// 에이작스 및 소 카테고리 및 필터적용 Mapper 및 무한스크롤
	@Override
	public List<ListVO> Productlist(ListVO list) {
		log.info("===============Productlist Service ServiceImple ============>" + productsmapper.Productlist(list));

		return productsmapper.Productlist(list);
	}

	// 메인 페이지 상품 리스트 가져오기
	@Override
	public List<MainProdVO> getmainprod() {
		log.info("productsmapper.getmainprod()" + productsmapper.getmainprod());
		return productsmapper.getmainprod();
	}
}
