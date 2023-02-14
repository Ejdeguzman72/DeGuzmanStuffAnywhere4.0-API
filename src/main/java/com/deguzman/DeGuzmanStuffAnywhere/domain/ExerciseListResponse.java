package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.ExerciseInfoDTO;

public class ExerciseListResponse {

	public List<ExerciseInfoDTO> list;

	public List<ExerciseInfoDTO> getList() {
		return list;
	}

	public void setList(List<ExerciseInfoDTO> list) {
		this.list = list;
	}
	
}
