package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.RestaurantDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RestaurantAddRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RestaurantListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RestaurantInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidRestaurantException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.RestaurantJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Restaurant;

@Service
public class RestaurantInfoService {

	@Autowired
	private RestaurantDaoImpl restaurantDaoImpl;
	
	@Autowired
	private RestaurantJpaDao restaurantJpaDao;
	
	public RestaurantListResponse findAllRestaurants() {
		RestaurantListResponse response = new RestaurantListResponse();
		List<RestaurantInfoDTO> list = restaurantDaoImpl.findAllRestaurants();
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<Map<String, Object>> getAllRestaurantsPagination(
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			List<Restaurant> shop = restaurantJpaDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Restaurant> pageBooks = null;

			if (name == null) {
				pageBooks = restaurantJpaDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("restaurants", shop);
			response.put("currentPage", pageBooks.getNumber());
			response.put("totalItems", pageBooks.getTotalElements());
			response.put("totalPages", pageBooks.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public RestaurantListResponse findAllRestaurantsByType(int restaurant_type_id) {
		RestaurantListResponse response = new RestaurantListResponse();
		List<RestaurantInfoDTO> list = restaurantDaoImpl.findAllRestaurantsByType(restaurant_type_id);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<RestaurantInfoDTO> findRestaurantById(int restaurant_id) throws InvalidRestaurantException {
		return restaurantDaoImpl.findRestaurantById(restaurant_id);
	}
	
	public RestaurantListResponse findRestaurantByZipCode(String zip) {
		RestaurantListResponse response = new RestaurantListResponse();
		List<RestaurantInfoDTO> list = restaurantDaoImpl.findRestaurantsByZipCode(zip);
		
		response.setList(list);
		return response;
	}
	
	public RestaurantListResponse findRestaurantsByDescr(String descr) {
		RestaurantListResponse response = new RestaurantListResponse();
		List<RestaurantInfoDTO> list = restaurantDaoImpl.findRestaurantsByDescr(descr);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<RestaurantInfoDTO> findRestaurantByName(String name) {
		return restaurantDaoImpl.findRestaurantByName(name);
	}
	
	public long getRestaurantCount() {
		return restaurantDaoImpl.getRestaurantCount();
	}
	
	public int addRestaurantInformation(RestaurantAddRequest request) throws ResourceNotFoundException, DuplicateRestaurantException {
		return restaurantDaoImpl.addRestaurantInformation(request);
	}
	
	public int updateRestaurantInformation(int restaurant_id, com.deguzman.DeGuzmanStuffAnywhere.model.Restaurant restaurantDetails) throws ResourceNotFoundException {
		return restaurantDaoImpl.updateRestaurantInformation(restaurant_id, restaurantDetails);
	}
	
	public int deleteRestaurantInformation(int restaurant_id) {
		return restaurantDaoImpl.deleteRestaurantInformation(restaurant_id);
	}
	
	public int deleteAllRestaurantInformation() {
		return restaurantDaoImpl.deleteAllRestaurantInformation();
	}
}
