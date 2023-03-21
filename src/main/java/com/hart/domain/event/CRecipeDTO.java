package com.hart.domain.event;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.jgroups.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Component
public class CRecipeDTO {
	String crid;
	String crtitle;
	MultipartFile crMimg;
	String crmingredient;
	String crlevel;
	String crtime;
	int crclick;
	Date crregdate;
	String crdetail;

	String[] crstep;
	String[] crcdetail;
	MultipartFile[] crimg;

	int[] cricount;
	String[] pid;
	int[] pcno;

	public CRecipeVO toCRecipeVO(String uploadPath) {
		CRecipeVO cr = new CRecipeVO();
		cr.setCrid(crid);
		cr.setCrtitle(crtitle);
		cr.setCrmingredient(crmingredient);
		cr.setCrlevel(crlevel);
		cr.setCrtime(crtime);
		cr.setCrregdate(crregdate);
		cr.setCrdetail(crdetail);

		String originalMName = crMimg.getOriginalFilename();
		String fileMName = originalMName.substring(originalMName.lastIndexOf("\\") + 1);
		String folderPath = makeFolder(uploadPath);

		// UUID
		String uuid = UUID.randomUUID().toString();
		String saveName = folderPath + File.separator + uuid + "_" + fileMName;
		Path savePath = Paths.get(uploadPath + File.separator + saveName);
		try {
			crMimg.transferTo(savePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cr.setCrMimg(saveName);

		return cr;

	}

	public List<CRContentVO> toCRContentVO(String uploadPath) {
		List<CRContentVO> CRContents = new LinkedList<>();

		for (int i = 0; i < crstep.length; i++) {
			CRContentVO cr = new CRContentVO();
			cr.setCrcdetail(crcdetail[i]);
			cr.setCrid(crid);
			cr.setStep(crstep[i]);

			String originalMName = crimg[i].getOriginalFilename();
			String fileMName = originalMName.substring(originalMName.lastIndexOf("\\") + 1);
			String folderPath = makeFolder(uploadPath);

			// UUID
			String uuid = UUID.randomUUID().toString();
			String saveName = folderPath + File.separator + uuid + "_" + fileMName;
			Path savePath = Paths.get(uploadPath + File.separator + saveName);
			try {
				crimg[i].transferTo(savePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cr.setCrimg(saveName);
			CRContents.add(cr);

		}

		return CRContents;

	}

	public List<CRIngredientVO> toCRIngredientVO() {
		List<CRIngredientVO> CRIngredients = new LinkedList<>();

		for (int i = 0; i < pid.length; i++) {
			CRIngredientVO cr = new CRIngredientVO();
			cr.setCrid(crid);
			cr.setCricount(cricount[i]);
			cr.setPid(pid[i]);
			cr.setPcno(pcno[i]);

			CRIngredients.add(cr);

		}

		return CRIngredients;

	}

	private String makeFolder(String uploadPath) {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("/", File.separator);

		File uploadPathFolder = new File(uploadPath, folderPath);
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}
}
