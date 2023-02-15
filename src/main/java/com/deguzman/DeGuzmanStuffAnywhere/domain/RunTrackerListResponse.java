package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;

public class RunTrackerListResponse {

	List<RunTrackerInfoDTO> list;

	public List<RunTrackerInfoDTO> getList() {
		return list;
	}

	public void setList(List<RunTrackerInfoDTO> list) {
		this.list = list;
	}
}
