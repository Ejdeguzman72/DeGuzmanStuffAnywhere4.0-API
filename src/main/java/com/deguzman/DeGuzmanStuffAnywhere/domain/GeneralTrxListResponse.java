package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.GeneralTrxInfoDTO;

public class GeneralTrxListResponse {

	List<GeneralTrxInfoDTO> list;

	public List<GeneralTrxInfoDTO> getList() {
		return list;
	}

	public void setList(List<GeneralTrxInfoDTO> list) {
		this.list = list;
	}
	
}
