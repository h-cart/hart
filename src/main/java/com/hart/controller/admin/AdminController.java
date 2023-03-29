package com.hart.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hart.domain.admin.AdminEventVO;
import com.hart.service.admin.AdminEventService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminEventService eService;

	@GetMapping("")
	public String home(@RequestParam(required = false) AdminEventVO event, Model model) {
		List<AdminEventVO> lists = eService.getList(event);
		model.addAttribute("lists", lists);
		log.info(lists);

		return "admin/main";
	}

	@GetMapping("/votelist")
	public String votelist(AdminEventVO event) {
		log.info(event);
		eService.voteList(event);
		return "redirect:/admin";
	}

}
