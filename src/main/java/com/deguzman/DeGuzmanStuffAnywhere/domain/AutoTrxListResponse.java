package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;

public class AutoTrxListResponse {

	public List<AutoTrxInfoDTO> list;

	public List<AutoTrxInfoDTO> getList() {
		return list;
	}

	public void setList(List<AutoTrxInfoDTO> list) {
		this.list = list;
	}
	
}
