package com.hart.controller.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.event.CRContentVO;
import com.hart.domain.event.CRIngredientVO;
import com.hart.domain.event.CRecipeDTO;
import com.hart.domain.event.CRecipeVO;
import com.hart.service.event.EventService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/event")
@Log4j2
public class EventController {
	@Value("${com.hart.upload.path}")
	private String uploadPath;

	@Autowired
	private EventService eventService;

	@GetMapping("")
	public String event() {
		return "event/event";
	}

	@GetMapping("/vote")
	public String vote() {
		
		return "event/vote";
	}

	@GetMapping("/upload")
	public void upload(Model model) {
		model.addAttribute("crecipe", new CRecipeDTO());
	}

	@PostMapping("/upload")
	public String register(CRecipeDTO crecipe) {
		if (crecipe.getCrMimg().getContentType().startsWith("image") == false) {
			return "event/upload";
		}
		log.info(crecipe);

		CRecipeVO recipe = crecipe.toCRecipeVO(uploadPath);
		List<CRContentVO> content = crecipe.toCRContentVO(uploadPath);
		List<CRIngredientVO> ingredient = crecipe.toCRIngredientVO();
		eventService.register(recipe, content, ingredient);
		return "event/event";
	}

}// end class
