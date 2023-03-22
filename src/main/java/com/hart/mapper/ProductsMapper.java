	package com.hart.mapper;
	
	
	import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hart.domain.CategoryVO;
import com.hart.domain.ProductCategorylistVO;
import com.hart.domain.ProductimgVO;
import com.hart.domain.ProductsVO;
	
	
	@Mapper
	public interface ProductsMapper {
		
		//1제품 목록 가져오기
		public List<ProductsVO> getproductslist(int pcno);
		
		//2대분류 카테고리 가져오기
		public List<CategoryVO> getcategorybar();
		
		//public List<CategoryVO> getcategorysmall(String pid);
		
		//3 제품 상페 이미지 가져오기
		public ProductsVO getproductDetail(String pid);
		
		//4 제품 상세 이미지 가져오기
		public List<ProductimgVO> getproductDetailimg(String pid);
	
		//public RecipeVO recipelist();
		
		//5카테고리 소분류 가져오기
		//public List<CategoryVO> getcategorysmall(int pcno);
		
		//6 카테고리 리스트 가
		public List<ProductCategorylistVO> getproductcatrogrtlist(int pcno);
		
		//합치려다 stop
		/* public List<ProductsidesamllVO> sideamllcategory(int pcno); */
	
	
		public List<ProductsVO> getproductslistajax(int pcno);
		
	}
