package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.authentication_models.User;

public class UserListResponse {

	public List<User> list;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}
}
