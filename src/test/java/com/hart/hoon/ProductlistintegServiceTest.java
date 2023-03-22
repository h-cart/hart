package com.hart.hoon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hart.service.RecipeService;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class ProductlistintegServiceTest {

	@Autowired
	private RecipeService rService;

	@Test
	public void get() {
		try {
			rService.recipelist().forEach(item -> log.info(item));
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
