package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.model.RestaurantType;

public class RestaurantTypeListResponse {

	List<RestaurantType> list;

	public List<RestaurantType> getList() {
		return list;
	}

	public void setList(List<RestaurantType> list) {
		this.list = list;
	}
}
