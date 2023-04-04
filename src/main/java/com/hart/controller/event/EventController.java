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
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
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
	public String event(int evid, Model model) {

		EventListVO event = eventService.getEvent(evid);
		log.info(event);
		model.addAttribute("event", event);
		return "event/event";
	}

	@GetMapping("/vote")
	public String vote(int evid, Model model) {
		List<CRecipeVO> recipe = eventService.getVoteList(evid);

		model.addAttribute("recipe", recipe);
		model.addAttribute("evid", evid);

		return "event/vote";
	}

	@PostMapping("/vote")
	public String voting(EventVoteVO ev, Model model) {
		eventService.voteRecipe(ev);
		return "redirect:/event?evid=" + ev.getEvid();
	}

	@GetMapping("/voteDetail")
	public String voteDetail(EventVoteVO ev, Model model) {
		CRecipeVO cr = eventService.getRecipeDetail(ev);

		model.addAttribute("detail", cr);
		return "event/voteDetail";
	}

	@GetMapping("/upload")
	public void upload(Model model, int evid) {
		EventListVO event = eventService.getEvent(evid);
		model.addAttribute("event", event);
		model.addAttribute("crecipe", new CRecipeDTO());
	}

	@PostMapping("/upload")
	public String register(CRecipeDTO crecipe) {
		log.info(crecipe);
		if (crecipe.getCrMimg().getContentType().startsWith("image") == false) {
			return "event/upload";
		}
		log.info(crecipe);

		CRecipeVO recipe = crecipe.toCRecipeVO(uploadPath);
		List<CRContentVO> content = crecipe.toCRContentVO(uploadPath);
		List<CRIngredientVO> ingredient = crecipe.toCRIngredientVO();
		eventService.register(recipe, content, ingredient);
		return "redirect:/event?evid=" + crecipe.getEvid();
	}

}// end class
