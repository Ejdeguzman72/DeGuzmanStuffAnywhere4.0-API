package com.deguzman.DeGuzmanStuffAnywhere.domain;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.RestaurantInfoDTO;

public class RestaurantListResponse {

	List<RestaurantInfoDTO> list;

	public List<RestaurantInfoDTO> getList() {
		return list;
	}

	public void setList(List<RestaurantInfoDTO> list) {
		this.list = list;
	}
}
