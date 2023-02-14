package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;

public class UtilityListResponse {

	List<UtilityInfoDTO> list;

	public List<UtilityInfoDTO> getList() {
		return list;
	}

	public void setList(List<UtilityInfoDTO> list) {
		this.list = list;
	}
}
