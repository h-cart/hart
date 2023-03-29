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
		
		//1제품 목록 가져오기
		public List<ProductsVO> getproductslist(int pcno);
		
		//2대분류 헤더 카테고리 가져오기 
		public List<CategoryVO> getcategorybar();
		
		//public List<CategoryVO> getcategorysmall(String pid);
		
		//3 제품 상페 이미지 가져오기
		public ProductsVO getproductDetail(String pid);
		
		//4 제품 상세 이미지 가져오기
		public List<ProductimgVO> getproductDetailimg(String pid);
	
		//public RecipeVO recipelist();
		
		//5카테고리 소분류 가져오기
		//public List<CategoryVO> getcategorysmall(int pcno);
		
		//6 왼쪽카테고리 리스트 가져오기
		public List<ListVO> getproductcatrogrtlist(int pcno);
		
		//합치려다 stop
		/* public List<ProductsidesamllVO> sideamllcategory(int pcno); */
	
		//소카테고리 클릭시 해당 카테고리 제품 부분만 가져오기
		//public List<ProductsVO> getproductslistajax(int pcno, int page);

		
		//합친것 
		//public List<ListVO> List(ListVO ListVO);


		public List<ListVO> Productlist(ListVO list);

		

		//public List<ProductsVO> List(ListVO ListVO);
		

	
		
	}
