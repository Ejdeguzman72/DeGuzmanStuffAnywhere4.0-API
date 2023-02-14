package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice;

public class MedicalOfficeListResponse {

	List<MedicalOffice> list;

	public List<MedicalOffice> getList() {
		return list;
	}

	public void setList(List<MedicalOffice> list) {
		this.list = list;
	}
}
