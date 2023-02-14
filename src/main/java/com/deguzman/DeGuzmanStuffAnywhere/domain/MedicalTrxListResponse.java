package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.MedicalTrxInfoDTO;

public class MedicalTrxListResponse {

	List<MedicalTrxInfoDTO> list;

	public List<MedicalTrxInfoDTO> getList() {
		return list;
	}

	public void setList(List<MedicalTrxInfoDTO> list) {
		this.list = list;
	}
	
}
