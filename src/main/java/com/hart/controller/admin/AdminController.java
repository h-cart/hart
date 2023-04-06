package com.hart.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.admin.PageDTO;
import com.hart.domain.event.EventListVO;
import com.hart.service.admin.AdminEventService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminEventService eService;
	
	
	// 관리자가 이벤트를 등록, 관리하는 페이지
	@GetMapping("/eventManage")	
	public String eventMange(Criteria cri, Model model) {
		List<EventListVO> lists = eService.getEventManageList(cri);
		
		
		int totalCnt = eService.getEventTotalCount();
		
		model.addAttribute("lists", lists);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCnt));
		
		return "admin/eventManage";
	}
	

	@GetMapping("")
	public String home(Criteria cri, Model model) {
		List<AdminEventVO> eventList = eService.getEventList();
		log.info(eventList);
		if (cri.getEvid() == 0) {
			cri.setEvid(eventList.get(0).getEvid());
			log.info("여기 실행:");
		}

		List<AdminEventVO> lists = eService.getList(cri);

		int totalCnt = eService.getTotalCount(cri);
		model.addAttribute("evid", cri.getEvid());
		model.addAttribute("lists", lists);
		model.addAttribute("eventLists", eventList);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCnt));

		return "admin/main";
	}

	@GetMapping("/recipeVote")
	public String recipeVote(Criteria cri, Model model) {
		List<AdminEventVO> eventList = eService.getEventList();
		log.info(cri);
		if (cri.getEvid() == 0) {
			cri.setEvid(eventList.get(0).getEvid());
		}

		List<AdminEventVO> lists = eService.getVoteList(cri);

		model.addAttribute("evid", cri.getEvid());
		model.addAttribute("lists", lists);
		model.addAttribute("eventLists", eventList);

		return "admin/recipeVote";
	}

	@GetMapping("/votelist")
	public String votelist(AdminEventVO event) {
		// log.info(event);
		eService.voteList(event);
		return "redirect:/admin?evid=" + event.getEvid();
	}

	@GetMapping("/recipeVotelist")
	public String recipeVotelist(AdminEventVO event) {
		// log.info(event);
		eService.voteList(event);
		return "redirect:/admin/recipeVote?evid=" + event.getEvid();
	}

	@GetMapping("/recipeRegister")
	public String recipeRegister(AdminEventVO event) {
		log.info("1. 여기 들어옴 " + event);
		eService.recipeRegister(event);
		return "redirect:/admin/recipeVote?evid=" + event.getEvid();
	}

	//이벤트 등록하는 페이지
	@GetMapping("/eventRegister")
	public String eventRegister(EventListVO event) {
		log.info("이벤트 등록 페이지");
		eService.eventRegister(event);
	
		return "admin/eventRegister";
	}
}
