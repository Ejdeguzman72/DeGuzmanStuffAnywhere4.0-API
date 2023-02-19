package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RestaurantInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Restaurant;

public interface RestaurantDao {

	public List<RestaurantInfoDTO> findAllRestaurants();

	public List<RestaurantInfoDTO> findAllRestaurantsByType(int restaurant_type_id);

	public List<RestaurantInfoDTO> findRestaurantsByZipCode(String zip);

	public List<RestaurantInfoDTO> findRestaurantsByDescr(String descr);

	public ResponseEntity<RestaurantInfoDTO> findRestaurantById(int restaurant_id)
			throws InvalidRestaurantException;

	public ResponseEntity<RestaurantInfoDTO> findRestaurantByName(String name);

	public long getRestaurantCount();

	public int addRestaurantInformation(Restaurant restaurant) throws ResourceNotFoundException, DuplicateRestaurantException;

	public int updateRestaurantInformation(int restaurant_id, Restaurant restaurantDetails)
			throws ResourceNotFoundException;

	public int deleteRestaurantInformation(int restaurant_id);

	public int deleteAllRestaurantInformation();

}
