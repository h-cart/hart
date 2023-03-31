package com.hart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.product.CategoryVO;
import com.hart.domain.product.ListVO;
import com.hart.domain.product.ProductsVO;
import com.hart.service.ProductsService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/papi")
@Log4j2
public class ProductRestController {

	@Autowired
	private ProductsService pService;
	private List<ListVO> productlist;

	@GetMapping(value = "/clist", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, List<CategoryVO>>> getCategory() {

		try {

			Map<String, List<CategoryVO>> result = new HashMap<>();

			List<CategoryVO> categorybar = pService.getcategorybar();

			result.put("result", categorybar);

			return new ResponseEntity<Map<String, List<CategoryVO>>>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.info(e.getMessage());
			return new ResponseEntity<Map<String, List<CategoryVO>>>(HttpStatus.BAD_REQUEST);
		}

	}

	
	
	
	@PostMapping(value = "/productlist", 
				produces = { MediaType.APPLICATION_JSON_VALUE }, 
				consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, List<ListVO>>> getProductsList(@RequestBody ListVO data) {

		try {

			 
			List<ListVO> Productlist = pService.Productlist(data);
			
			  log.info("data=====>>>>"+data); 
			  log.info("getproductslis컨트롤러에서 찍힘>>>" +  Productlist); log.info(Productlist);
			 
			
			
			Map<String, List<ListVO>> result = new HashMap<>();
			result.put("productlist", Productlist);
			
			
			log.info("result--->>"+result);
			
			
			return new ResponseEntity<Map<String, List<ListVO>>>(result, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, List<ListVO>>>(HttpStatus.BAD_REQUEST);
		}
	}


	/*
	 * @PostMapping(value = "/fillterbutton", produces = {
	 * MediaType.APPLICATION_JSON_VALUE }, consumes = {
	 * MediaType.APPLICATION_JSON_VALUE }) public ResponseEntity<Map<String,
	 * List<ListVO>>> getproductlisthigh(
	 * 
	 * @RequestBody ListVO data) {
	 * 
	 * try { log.info(data); List<ListVO> listtop = pService.List(data); //
	 * log.info("listtop=>>>" + listtop);
	 * 
	 * Map<String, List<ListVO>> result = new HashMap<>();
	 * result.put("getproductslist", listtop);
	 * 
	 * return new ResponseEntity<>(result, HttpStatus.OK);
	 * 
	 * } catch (Exception e) { log.info(e.getMessage()); return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } }
	 */

}