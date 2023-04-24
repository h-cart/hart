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


/**
 * @since : 2023. 03. 15.
 * @FileName: EventController.java
 * @author : 이승규
 * @설명 : 공모전 관련 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 22.     이승규       register 구현
 * 2023. 03. 27.     이승규       event 구현
 * 2023. 03. 27.     이승규       vote 구현
 * 2023. 03. 27.     이승규       voting 구현
 * 2023. 03. 29.     이승규       upload 구현
 * 2023. 03. 30.     이승규       voteDetail 구현
 *     </pre>
 */
@Controller
@RequestMapping("/event")
@Log4j2
public class EventController {

	@Value("${com.hart.upload.path}")
	private String uploadPath;

	@Autowired
	private EventService eventService;

	//공모전 페이지 구현
	@GetMapping("")
	public String event(int evid, Model model) {
		EventListVO event = eventService.getEvent(evid);
		model.addAttribute("event", event);
		return "event/event";
	}

	//투표페이지 구현 
	@GetMapping("/vote")
	public String vote(int evid, Model model) {
		List<CRecipeVO> recipe = eventService.getVoteList(evid);
		model.addAttribute("recipe", recipe);
		model.addAttribute("evid", evid);
		return "event/vote";
	}

	// 투표하기 구현
	@PostMapping("/vote")
	public String voting(EventVoteVO ev, Model model) {
		eventService.voteRecipe(ev);
		return "redirect:/event?evid=" + ev.getEvid();
	}

	//투표 상세 구현 
	@GetMapping("/voteDetail")
	public String voteDetail(EventVoteVO ev, Model model) {
		CRecipeVO cr = eventService.getRecipeDetail(ev);
		model.addAttribute("detail", cr);
		return "event/voteDetail";
	}

	// 레시피 업로드 페이지
	@GetMapping("/upload")
	public void upload(Model model, int evid) {
		EventListVO event = eventService.getEvent(evid);
		model.addAttribute("event", event);
		model.addAttribute("crecipe", new CRecipeDTO());
	}

	//레시피 업로드 기능 구현
	@PostMapping("/upload")
	public String register(CRecipeDTO crecipe) {
		if (crecipe.getCrMimg().getContentType().startsWith("image") == false) {
			return "event/upload";
		}
		CRecipeVO recipe = crecipe.toCRecipeVO(uploadPath);
		List<CRContentVO> content = crecipe.toCRContentVO(uploadPath);
		List<CRIngredientVO> ingredient = crecipe.toCRIngredientVO();
		eventService.register(recipe, content, ingredient);
		return "redirect:/event?evid=" + crecipe.getEvid();
	}
}// end class
