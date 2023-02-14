package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.PostDTO;

public class PostListResponse {

	List<PostDTO> list;

	public List<PostDTO> getList() {
		return list;
	}

	public void setList(List<PostDTO> list) {
		this.list = list;
	}
}
