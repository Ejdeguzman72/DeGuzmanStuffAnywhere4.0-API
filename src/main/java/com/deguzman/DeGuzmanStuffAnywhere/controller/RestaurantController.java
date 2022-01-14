package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.RestaurantDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RestaurantInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Restaurant;

@RestController
@RequestMapping("/app/restaurants")
@CrossOrigin
public class RestaurantController {

	@Autowired
	private RestaurantDaoImpl restaurantDaoImpl;
	
	@GetMapping("/all")
	public List<RestaurantInfoDTO> getAllRestaurantInformation() {
		return restaurantDaoImpl.findAllRestaurants();
	}
	
	@GetMapping("/restaurant/type/{restaurant_type_id}")
	public List<RestaurantInfoDTO> getAllRestaurantInformationByType(@PathVariable int restaurant_type_id) {
		return restaurantDaoImpl.findAllRestaurantsByType(restaurant_type_id);
	}
	
	@GetMapping("/restaurant/zip/{zip}")
	public List<RestaurantInfoDTO> getAllRestaurantInformationByZip(@PathVariable String zip) {
		return restaurantDaoImpl.findRestaurantsByZipCode(zip);
	}
	
	@GetMapping("/restaurant/descr/{descr}")
	public List<RestaurantInfoDTO> getAllRestaurantInformationByDescr(@PathVariable String descr) {
		return restaurantDaoImpl.findRestaurantsByDescr(descr);
	}
	
	@GetMapping("/restaurant/{restaurant_id}")
	public ResponseEntity<RestaurantInfoDTO> getRestaurantInformationById(@PathVariable int restaurant_id) throws InvalidRestaurantException {
		return restaurantDaoImpl.findRestaurantById(restaurant_id);
	}
	
	@GetMapping("/restaurant/name/{name}")
	public ResponseEntity<RestaurantInfoDTO> getRestaurantInformationByName(@PathVariable String name) {
		return restaurantDaoImpl.findRestaurantByName(name);
	}
	
	@GetMapping("/restaurant-count")
	public long getRestaurantCount() {
		return restaurantDaoImpl.getRestaurantCount();
	}
	
	@PostMapping("/add-restaurant-information")
	public int addRestaurantInformation(@RequestBody Restaurant restaurant) throws ResourceNotFoundException {
		return restaurantDaoImpl.addRestaurantInformation(restaurant);
	}
	
	@DeleteMapping("/restaurant/{restaurant_id}")
	public int deleteRestaurantInformationById(@PathVariable int restaurant_id) {
		return restaurantDaoImpl.deleteRestaurantInformation(restaurant_id);
	}
	
	@DeleteMapping("/delete-all-restaurant")
	public int deleteAllRestaurantInformation() {
		return restaurantDaoImpl.deleteAllRestaurantInformation();
	}
}