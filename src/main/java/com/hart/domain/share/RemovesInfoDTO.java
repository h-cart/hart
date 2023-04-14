package com.hart.domain.share;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemovesInfoDTO {
	
	private String mid;
	private String mname;
	private String pname;
	private List<String> pids;
	private int count;
}
