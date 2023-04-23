package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.MainProdVO;
import com.hart.domain.product.ProductimgVO;
import com.hart.domain.product.ProductsVO;

/**
 * @since : 2023. 03.16.
 * @FileName: ProductsMapper.java
 * @author : 박정훈
 * @설명 : 데이터베이스 마이바티스 인터페이스
 * 
 */
@Mapper
public interface ProductsMapper {

	// 제품 목록 가져오기
	public List<ProductsVO> getproductslist(int pcno);

	// 대분류 헤더 카테고리 가져오기
	public List<CategoryVO> getcategorybar();

	//  제품 상페 이미지 가져오기
	public ProductsVO getproductDetail(String pid);

	//  제품 상세 이미지 가져오기
	public List<ProductimgVO> getproductDetailimg(String pid);

	//  왼쪽카테고리 리스트 가져오기
	public List<ListVO> getproductcatrogrtlist(int pcno);

	// 에이작스 및 소 카테고리 및 필터적용 및 무한스크롤
	public List<ListVO> Productlist(ListVO list);

	// 상품상단의 카테고리 가져오기
	public List<CategoryVO> getsmallcategorybar(int pcno);

	// 메인페이지 상품 리스트 가져오기
	public List<MainProdVO> getmainprod();

}