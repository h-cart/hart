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
import lombok.extern.log4j.Log4j2;

@Data
@Component
@Log4j2
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
	String mid;
	int evid;

	String[] crstep;
	String[] crcdetail;
	MultipartFile[] crimg;

	String[] cricount;
	String[] pid;
	String[] pcno;
	String[] iname;

	public CRecipeVO toCRecipeVO(String uploadPath) {
		CRecipeVO cr = new CRecipeVO();
		cr.setCrid(crid);
		cr.setCrtitle(crtitle);
		cr.setCrmingredient(crmingredient);
		cr.setCrlevel(crlevel);
		cr.setCrtime(crtime);
		cr.setCrregdate(crregdate);
		cr.setCrdetail(crdetail);
		cr.setMid(mid);
		cr.setEvid(evid);
		String originalMName = crMimg.getOriginalFilename();
		String fileMName = originalMName.substring(originalMName.lastIndexOf("\\") + 1);
		String folderPath = makeFolder(uploadPath);

		// UUID
		String uuid = UUID.randomUUID().toString();
		String saveName = folderPath + uuid + "_" + fileMName;
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
			String saveName = folderPath + uuid + "_" + fileMName;
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
			if(pid[i].equals("")){
				cr.setPid("null");
			}else{
				cr.setPid(pid[i]);
			}
			cr.setIname(iname[i]);
			

			CRIngredients.add(cr);

		}

		return CRIngredients;

	}

	private String makeFolder(String uploadPath) {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		/* String folderPath = str.replace("/", File.separator); */
		String folderPath = str;
		File uploadPathFolder = new File(uploadPath, folderPath);
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}
}
