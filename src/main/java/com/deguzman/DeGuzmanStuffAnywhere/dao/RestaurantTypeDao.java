package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.model.RestaurantType;

public interface RestaurantTypeDao {

	public List<RestaurantType> findAllRestaurantTypeInformation();

	public ResponseEntity<RestaurantType> findRestaurantInformationById(int restaurant_type_id);

	public ResponseEntity<RestaurantType> findRestaurantTypeByDescr(String restaurantDescr);

	public long getRestaurantTypeCount();
}
