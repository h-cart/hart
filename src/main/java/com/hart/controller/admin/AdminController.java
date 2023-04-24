package com.hart.controller.admin;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hart.domain.admin.AdminEventVO;
import com.hart.domain.admin.AdminLiveClassDTO;
import com.hart.domain.admin.Criteria;
import com.hart.domain.admin.PageDTO;
import com.hart.domain.event.CRecipeVO;
import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
import com.hart.service.admin.AdminEventService;
import com.hart.service.event.EventService;
import com.hart.service.liveClass.LiveClassService;

import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 03. 27.
 * @FileName: AdminController.java
 * @author : 이승규
 * @설명 : 공모전 관리자 관련 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 27.     이승규       AdminController 구현
 * 2023. 03. 29.     이승규       votelist 구현
 * 2023. 04. 03.     이승규       recipeVote 구현
 * 2023. 04. 03.     이승규       recipeVotelist 구현
 * 2023. 04. 04.     이승규       recipeRegister 구현
 * 2023. 04. 06.     이승규       eventMange 구현
 * 2023. 04. 06.     이승규       eventRegister 구현
 * 2023. 04. 06.     이승규       eventModify 구현
 * 2023. 04. 07.     이승규       recipePreview 구현
 * 2023. 04. 10.     함세강       liveClassDetailTest 구현
 *     </pre>
 */
@Controller
@Log4j2
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminEventService eService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private LiveClassService classService;
	
	
	// 관리자가 이벤트를 등록, 관리하는 페이지
	@GetMapping("/eventManage")	
	public String eventMange(Criteria cri, Model model) {
		List<EventListVO> lists = eService.getEventManageList(cri);
		int totalCnt = eService.getEventTotalCount();
		
		model.addAttribute("lists", lists);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCnt));
		
		return "admin/eventManage";
	}
	
	//어드민 메인페이지 (이벤트 리스트)
	@GetMapping("")
	public String home(Criteria cri, Model model) {
		List<AdminEventVO> eventList = eService.getEventList();
		if (cri.getEvid() == 0) {
			cri.setEvid(eventList.get(0).getEvid());
		}

		List<AdminEventVO> lists = eService.getList(cri);

		int totalCnt = eService.getTotalCount(cri);
		model.addAttribute("evid", cri.getEvid());
		model.addAttribute("lists", lists);
		model.addAttribute("eventLists", eventList);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCnt));

		return "admin/main";
	}
	
	//어드민 메인페이지 (레시피 투표 페이지지)
	@GetMapping("/recipeVote")
	public String recipeVote(Criteria cri, Model model) {
		List<AdminEventVO> eventList = eService.getEventList();
	
		if (cri.getEvid() == 0) {
			cri.setEvid(eventList.get(0).getEvid());
		}
		List<AdminEventVO> lists = eService.getVoteList(cri);

		model.addAttribute("evid", cri.getEvid());
		model.addAttribute("lists", lists);
		model.addAttribute("eventLists", eventList);

		return "admin/recipeVote";
	}
	
	//어드민 메인페이지 (레시피 투표 리스트)
	@GetMapping("/votelist")
	public String votelist(AdminEventVO event) {
		eService.voteList(event);
		return "redirect:/admin?evid=" + event.getEvid();
	}

	//어드민 메인페이지 (레시피 투표하기)
	@GetMapping("/recipeVotelist")
	public String recipeVotelist(AdminEventVO event) {
		eService.voteList(event);
		return "redirect:/admin/recipeVote?evid=" + event.getEvid();
	}

	//어드민 메인페이지 (레시피 등록하기)
	@GetMapping("/recipeRegister")
	public String recipeRegister(AdminEventVO event) {
		eService.recipeRegister(event);
		return "redirect:/admin/recipeVote?evid=" + event.getEvid();
	}

	//이벤트 등록하는 페이지
	@GetMapping("/eventRegister")
	public String eventRegister(@ModelAttribute EventListVO event) {
		eService.eventRegister(event);
		return "redirect:/admin/eventManage";
	}

	// 이벤트 수정하는 페이지
	@GetMapping("/eventModify")
	public String eventModify(@ModelAttribute EventListVO event) {
		log.info(event);
		eService.eventModify(event);
		return "redirect:/admin/eventManage";
	}
	
	// 이벤트 미리보기 (새창 열기)
	@GetMapping("/recipePreview")
	public String recipePreview(EventVoteVO ev, Model model) {
		CRecipeVO cr = eventService.getRecipeDetail(ev);
		model.addAttribute("detail", cr);
		return "admin/recipePreview";
	}
	
	// 라이브 클래스 관리 페이지
	@GetMapping("/class")
	public String liveClassDetailTest(Model model) {
		List<AdminLiveClassDTO> list;
		try {
			list = classService.getClassAdminList();
			model.addAttribute("classList",list);
		} catch (SQLException e) {
			log.info(e.getMessage());
		}

		return "admin/classManage";
	}
	
	
}
