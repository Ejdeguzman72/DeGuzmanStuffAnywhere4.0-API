package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.Restaurant;

public class RestaurantAddUpdateResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899192843285005625L;

	Restaurant restaurant;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
