package com.hart.domain.liveClass;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//받아온 정보를 가공해서 뷰단에 뿌려주기 용이하게 담는 Domain
@Getter
@Setter
@ToString
public class LiveClassDetailInfoDTO {
	private String[] ingredientList;
	private String[] lctExplainList;
	private String[] lcStudentList;
	private String wholeDate;
	private LiveClassListDTO liveClassListDTO;
}
