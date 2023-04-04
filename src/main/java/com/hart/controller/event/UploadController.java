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

import com.hart.domain.product.ProductsVO;
import com.hart.service.event.EventService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/event/api")
public class UploadController {

	@Value("${com.hart.upload.path}")
	private String uploadPath;
	@Autowired
	private EventService eventService;

	@PostMapping("/uploadAjax")
	public void uploadFile(MultipartFile[] uploadFiles) {

		log.info(uploadFiles);
		for (MultipartFile i : uploadFiles) {
			// [IE] 실제 파일 이름 전체 경로가 들어오므로
			// String fileName =
			// originalName.substring(originalName.lastIndexOf("\\"+1));
			String originalName = i.getOriginalFilename();
			log.info("fileName :" + originalName);
		} // end for

	}// end void

	@GetMapping("/search")
	public ResponseEntity<List<ProductsVO>> searchProduct(String keyword) throws SQLException {
		return ResponseEntity.ok(eventService.getList(keyword));
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> displayImg(@RequestParam("imgName") String imgName) {
		log.info("sdfs");
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
}// end class
