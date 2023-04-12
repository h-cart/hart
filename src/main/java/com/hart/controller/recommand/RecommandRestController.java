package com.hart.controller.recommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hart.domain.member.ClubAuthMemberDTO;
import com.hart.domain.recommand.IngredientDTO;
import com.hart.domain.recommand.RecommandDTO;
import com.hart.service.cart.CartService;
import com.hart.service.recommand.RecommandService;
import com.hart.service.share.ShareService;

@RestController
@RequestMapping("/recommand")
public class RecommandRestController {
	@Autowired
	private RecommandService rService;

	@Autowired
	private CartService cService;

	@Autowired
	private ShareService sService;

	@GetMapping(value = "/list", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, RecommandDTO>> getRecommandList(@AuthenticationPrincipal ClubAuthMemberDTO mDTO) {
		Map<String, RecommandDTO> result = new HashMap<>();
		try {
			result.put("result", rService.getRecommand(mDTO.getMid(), mDTO.getCsno()));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/products/{pid}/recommendations", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, RecommandDTO>> getRecommands(@PathVariable("pid") String pid) {

		return null;
	}

	@PostMapping(value = "/class/recommendations", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, List<IngredientDTO>>> RecommandForClass(
			@AuthenticationPrincipal ClubAuthMemberDTO mDTO,@RequestBody Map<String,List<String>>map) {
		Map<String, List<IngredientDTO>> result = new HashMap<>();
		try {
			List<String> pids = map.get("pids");
			List<String> pamounts = map.get("pamounts");

			if (mDTO.getCsno() != null) {
				sService.cartInsert(pids, pamounts, Integer.parseInt(mDTO.getCsno()));
			} else {
				cService.cartInsert(pids, pamounts, mDTO.getMid());
			}
			result.put("result", rService.getIngredients(pids.get(0)));
			return new ResponseEntity<Map<String, List<IngredientDTO>>>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, List<IngredientDTO>>>(HttpStatus.BAD_REQUEST);
		}
	}

}
