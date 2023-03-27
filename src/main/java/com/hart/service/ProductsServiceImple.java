package com.hart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.FillterVO;
import com.hart.domain.product.ProductCategorylistVO;
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

		// log.info("#############productVO getProductList @@@@@@@@@=" + pcno);
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

	/*
	 * @Override//5카테고리 소분류 가져오기 public List<CategoryVO> getcategorysmall(int pcno)
	 * {
	 * 
	 * return productsmapper.getcategorysmall(pcno); }
	 */

	@Override // 6 카테고리 리스트 가져오기
	public List<ProductCategorylistVO> getproductcatrogrtlist(int pcno) {
		// log.info("===============getproductcatrogrtlist getproductcatrogrtlist
		// Service ============" + pcno);
		return productsmapper.getproductcatrogrtlist(pcno);
	}

	
	@Override // 6 카테고리 리스트 가져오기
	public List<ProductsVO> getproductslistajax(int pcno,int page) {

		return productsmapper.getproductslistajax(pcno,page);
	}

	/*
	 * @Override public List<ProductlistintegVO> Productlistinteg(int pcno) { //3 제품
	 * 상페 이미지 가져오기
	 * 
	 * List<ProductsVO> productlist = productsmapper.getproductslist(pcno);
	 * 
	 * System.out.println("productlistServiceImple ======="+ productlist);
	 * 
	 * List<ProductsidesamllVO> smallcategory
	 * =productsmapper.sideamllcategory(pcno);
	 * 
	 * System.out.println("smallcategorytServiceImple ======="+ smallcategory); //4
	 * 제품 상세 이미지 가져오기 return new ArrayList<>(Arrays.asList(new
	 * ProductlistintegVO(productlist, smallcategory)));
	 * 
	 * 
	 * }
	 */


	@Override
	public List<ProductsVO> fillter(FillterVO fVO) {
		List<ProductsVO> result = productsmapper.fillter(fVO);
		return result;
	}


}
