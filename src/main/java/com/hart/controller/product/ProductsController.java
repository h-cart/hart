package com.hart.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.product.ListVO;
import com.hart.domain.product.ProductsDetailVO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.service.ProductsService;
import com.hart.service.recommand.RecommandService;

import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 03. 21.
 * @FileName: ProductsController.java
 * @author : 박정훈
 * @설명 : 상품 리스트 및 상품 상세 컨트롤러
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 15.    박정훈       ProductsController 구현
 * 2023. 03. 18.    박정훈       list 페이지 구현
 * 2023. 03. 23.    박정훈       productDetail 페이지 구현
 * 2023. 03. 26.    박정훈       product 수정
 */

@Controller
@Log4j2
@RequestMapping("/product")
public class ProductsController {

	
	
   @Autowired
   private ProductsService productsservice;

   @Autowired
   private RecommandService rService;
   
   //레시피 목록 페이지 
   @GetMapping("/list")
   public String productlist(Model model, ListVO list) {

      try {
       List<ListVO> cList = productsservice.getproductcatrogrtlist(list.getPcno_top());
       //왼족 카테고리 리스트
       model.addAttribute("cList",cList); 
         
       // input으로 넣어줌pcno_top을 히든으로 
       model.addAttribute("pcno_top",list.getPcno_top());
         
       model.addAttribute("pcno", list.getPcno());
       
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "product/productList";
   }

   //레시피 상세 페이지 
   @GetMapping("/productDetail")
   public String ProductDtail(String pid, Model model) {

      try {
    	  
    	 RecommandDTO rDTO = rService.RecommandForProduct(pid);
         ProductsDetailVO Detail = productsservice.getProductDetails(pid);
         model.addAttribute("Detail", Detail);
         model.addAttribute("recipes",rDTO.getRecipes());
         model.addAttribute("lives",rDTO.getLives());
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "product/productDetail";
   }

}