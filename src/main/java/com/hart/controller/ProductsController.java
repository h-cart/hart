package com.hart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.product.ListVO;
import com.hart.domain.product.ProductsDetailVO;
import com.hart.service.ProductsService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/product")
public class ProductsController {

	//private ChatGPT bot;
	
   @Autowired
   private ProductsService productsservice;
	/*
	 * private Object cList; private Object productlist;
	 */

   @GetMapping("/list")
   public String productlist(Model model, ListVO list) {

      log.info("pcno Productcontroller @@@@@@@@@@@@@@@ list.getPcno_top()="+ list.getPcno_top() + list.getPcno());
      
      try {
         
         List<ListVO> cList = productsservice.getproductcatrogrtlist(list.getPcno_top());
         
         System.out.println(cList + "<<Clist 컨트롤러 카테고리 리스트만 가져옴");//
         
         model.addAttribute("cList",cList); //왼족 카테고리 리스트\
         
          log.info("cList>>>>>"+cList);
         model.addAttribute("pcno_top",list.getPcno_top());// input으로 넣어줌pcno_top을 히든으로 
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return "product/productList";
   }



   @GetMapping("/productDetail")
   public String ProductDtail(String pid, Model model) {

      try {

         ProductsDetailVO Detail = productsservice.getProductDetails(pid);

          System.out.println("Detail ==================>>>>>>" + Detail);

         model.addAttribute("Detail", Detail);

      } catch (Exception e) {
         e.printStackTrace();
      }

      return "product/productDetail";

   }

}