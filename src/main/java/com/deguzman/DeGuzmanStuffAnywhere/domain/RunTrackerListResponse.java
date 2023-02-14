package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker;

public class RunTrackerListResponse {

	List<RunTracker> list;

	public List<RunTracker> getList() {
		return list;
	}

	public void setList(List<RunTracker> list) {
		this.list = list;
	}
}
