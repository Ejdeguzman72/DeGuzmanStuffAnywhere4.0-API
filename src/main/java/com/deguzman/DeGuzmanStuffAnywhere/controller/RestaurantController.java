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
import com.deguzman.DeGuzmanStuffAnywhere.domain.RestaurantSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByDescr;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByZipRequest;
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
	public RestaurantListResponse getAllRestaurantInformationByType(@RequestBody @Valid SearchByIntRequest request) {
		RestaurantListResponse response = restaurantInfoService.findAllRestaurantsByType(request);
		return response;
	}

	@GetMapping("/restaurant/zip/{zip}")
	@CrossOrigin
	public RestaurantListResponse getAllRestaurantInformationByZip(@RequestBody @Valid SearchByZipRequest request) {
		RestaurantListResponse response = restaurantInfoService.findRestaurantByZipCode(request);
		return response;
	}

	@GetMapping("/restaurant/descr/{descr}")
	@CrossOrigin
	public RestaurantListResponse getAllRestaurantInformationByDescr(@RequestBody @Valid SearchByDescr request) {
		RestaurantListResponse response = restaurantInfoService.findRestaurantsByDescr(request);
		return response;
	}

	@GetMapping("/restaurant-dto/{restaurant_id}")
	@CrossOrigin
	public RestaurantSearchResponse getRestaurantDTOInfoById(@RequestBody @Valid SearchByIntRequest request)
			throws InvalidRestaurantException {
		RestaurantSearchResponse response = restaurantInfoService.findRestaurantById(request);
		return response;
	}
	
	@GetMapping("/restaurant/{restaurant_id}")
	public RestaurantSearchResponse getRestaurantInfoById(@RequestBody @Valid SearchByIntRequest request) throws InvalidRestaurantException {
		RestaurantSearchResponse response = restaurantInfoService.findRestaurantById(request);
		return response;
	}
	

	@GetMapping("/restaurant/name/{name}")
	@CrossOrigin
	public RestaurantSearchResponse getRestaurantInformationByName(@RequestBody @Valid SearchByNameRequest request) {
		RestaurantSearchResponse response = restaurantInfoService.findRestaurantByName(request);
		return response;
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
	public int deleteRestaurantInformationById(@RequestBody @Valid SearchByIntRequest request) {
		return restaurantInfoService.deleteRestaurantInformation(request);
	}

	@DeleteMapping("/delete-all-restaurant")
	@CrossOrigin
	public DeleteAllResponse deleteAllRestaurantInformation() {
		DeleteAllResponse response = restaurantInfoService.deleteAllRestaurantInformation();
		return response;
	}
}
