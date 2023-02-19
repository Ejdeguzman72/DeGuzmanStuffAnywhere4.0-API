package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RestaurantAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RestaurantAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RestaurantListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RestaurantInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.service.RestaurantInfoService;

@RestController
@RequestMapping("/app/restaurants")
@CrossOrigin
public class RestaurantController {
	
	@Autowired
	private RestaurantInfoService restaurantInfoService;

	@GetMapping("/all")
	@CrossOrigin
	public RestaurantListResponse getAllRestaurantInformation() {
		RestaurantListResponse response = restaurantInfoService.findAllRestaurants();
		return response;
	}
	
	@GetMapping("/all-restaurants")
	public ResponseEntity<Map<String, Object>> getAllRestaurantsPagination(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return restaurantInfoService.getAllRestaurantsPagination(name, page, size);
	}

	@GetMapping("/restaurant/type/{restaurant_type_id}")
	@CrossOrigin
	public RestaurantListResponse getAllRestaurantInformationByType(@PathVariable int restaurant_type_id) {
		RestaurantListResponse response = restaurantInfoService.findAllRestaurantsByType(restaurant_type_id);
		return response;
	}

	@GetMapping("/restaurant/zip/{zip}")
	@CrossOrigin
	public RestaurantListResponse getAllRestaurantInformationByZip(@PathVariable String zip) {
		RestaurantListResponse response = restaurantInfoService.findRestaurantByZipCode(zip);
		return response;
	}

	@GetMapping("/restaurant/descr/{descr}")
	@CrossOrigin
	public RestaurantListResponse getAllRestaurantInformationByDescr(@PathVariable String descr) {
		RestaurantListResponse response = restaurantInfoService.findRestaurantsByDescr(descr);
		return response;
	}

	@GetMapping("/restaurant-dto/{restaurant_id}")
	@CrossOrigin
	public ResponseEntity<RestaurantInfoDTO> getRestaurantDTOInfoById(@PathVariable int restaurant_id)
			throws InvalidRestaurantException {
		return restaurantInfoService.findRestaurantById(restaurant_id);
	}
	
	@GetMapping("/restaurant/{restaurant_id}")
	public ResponseEntity<RestaurantInfoDTO> getRestaurantInfoById(@PathVariable int restaurant_id) throws InvalidRestaurantException {
		return restaurantInfoService.findRestaurantById(restaurant_id);
	}
	

	@GetMapping("/restaurant/name/{name}")
	@CrossOrigin
	public ResponseEntity<RestaurantInfoDTO> getRestaurantInformationByName(@PathVariable String name) {
		return restaurantInfoService.findRestaurantByName(name);
	}

	@GetMapping("/restaurant-count")
	@CrossOrigin
	public long getRestaurantCount() {
		return restaurantInfoService.getRestaurantCount();
	}

	@PostMapping("/add-restaurant-information")
	@CrossOrigin
	public RestaurantAddUpdateResponse addRestaurantInformation(@RequestBody @Valid RestaurantAddUpdateRequest request) throws ResourceNotFoundException, DuplicateRestaurantException {
		RestaurantAddUpdateResponse response = restaurantInfoService.addRestaurantInformation(request);
		return response;
	}

	@PutMapping("/restaurant/{restaurant_id}")
	@CrossOrigin
	public RestaurantAddUpdateResponse updateRestaurantInformation(@RequestBody @Valid RestaurantAddUpdateRequest request) throws ResourceNotFoundException {
		RestaurantAddUpdateResponse response = restaurantInfoService.updateRestaurantInformation(request);
		return response;
	}
	
	@DeleteMapping("/restaurant/{restaurant_id}")
	@CrossOrigin
	public int deleteRestaurantInformationById(@PathVariable int restaurant_id) {
		return restaurantInfoService.deleteRestaurantInformation(restaurant_id);
	}

	@DeleteMapping("/delete-all-restaurant")
	@CrossOrigin
	public DeleteAllResponse deleteAllRestaurantInformation() {
		DeleteAllResponse response = restaurantInfoService.deleteAllRestaurantInformation();
		return response;
	}
}
