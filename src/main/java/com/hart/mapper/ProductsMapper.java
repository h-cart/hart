package com.hart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.ProductCategorylistVO;
import com.hart.domain.product.ProductimgVO;
import com.hart.domain.product.ProductsVO;

@Mapper
public interface ProductsMapper {

   // 1제품 목록 가져오기
   public List<ProductsVO> getproductslist(int pcno);

   // 2대분류 헤더 카테고리 가져오기
   public List<CategoryVO> getcategorybar();


   // 3 제품 상페 이미지 가져오기
   public ProductsVO getproductDetail(String pid);

   // 4 제품 상세 이미지 가져오기
   public List<ProductimgVO> getproductDetailimg(String pid);

   // 6 왼쪽카테고리 리스트 가져오기
   public List<ListVO> getproductcatrogrtlist(int pcno);

   //에이작스 및 소 카테고리 및 필터적용 Mapper
   public List<ListVO> Productlist(ListVO list);



}