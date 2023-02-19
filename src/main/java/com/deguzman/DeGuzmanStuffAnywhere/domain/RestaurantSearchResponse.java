package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.RestaurantInfoDTO;

public class RestaurantSearchResponse {

	public RestaurantInfoDTO restaurant;

	public RestaurantInfoDTO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantInfoDTO restaurant) {
		this.restaurant = restaurant;
	}
}
