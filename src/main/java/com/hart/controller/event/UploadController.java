package com.hart.controller.event;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hart.domain.event.EventListVO;
import com.hart.domain.event.EventVoteVO;
import com.hart.domain.product.ProductsVO;
import com.hart.service.event.EventService;

import lombok.extern.log4j.Log4j2;

/**
 * @since : 2023. 03. 15.
 * @FileName: UploadController.java
 * @author : 이승규
 * @설명 : 공모전 관련 요청 처리 컨트롤러
 * 
 *     <pre>
 *   수정일         수정자               수정내용
 * ----------      --------    ---------------------------
 * 2023. 03. 17.     이승규       searchProduct 구현
 * 2023. 03. 30.     이승규       displayImg 구현
 * 2023. 04. 10.     이승규       checkVote 구현
 * 2023. 04. 14.     이승규       getEventId 구현
 *     </pre>
 */
@RestController
@Log4j2
@RequestMapping("/event/api")
public class UploadController {

	@Value("${com.hart.upload.path}")
	private String uploadPath;
	@Autowired
	private EventService eventService;
	
	//추천 검색어
	@GetMapping("/search")
	public ResponseEntity<List<ProductsVO>> searchProduct(String keyword) throws SQLException {
		return ResponseEntity.ok(eventService.getList(keyword));
	}

	// 이미지 가져오기
	@GetMapping("/display")
	public ResponseEntity<byte[]> displayImg(@RequestParam("imgName") String imgName) {
		ResponseEntity<byte[]> result = null;
		try {
			String srcFileName = URLDecoder.decode(imgName, "UTF-8");

			File file = new File(uploadPath + File.separator + srcFileName);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// 투표 했는지 확인
	@GetMapping("/checkVote")
	public ResponseEntity<Integer> checkVote(EventVoteVO event )
			throws SQLException {
		return ResponseEntity.ok(eventService.checkVote(event));
	}

	// 이벤트 목록 가져오기
	@GetMapping("/eventId")
	public ResponseEntity<EventListVO> getEventId() throws SQLException {
		return ResponseEntity.ok(eventService.getEventId());
	}
}// end class
