package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByZipRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.service.AutoRepairShopService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AutoShopController {

	@Autowired
	private AutoRepairShopService autoShopService;

	@GetMapping(value = UriConstants.GET_ALL_AUTO_SHOP)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopListResponse getAllAutoRepairShopInformation() {
		AutoShopListResponse response = autoShopService.findAllAutoRepairShopInfo();
		return response;
	}

	@GetMapping(value = UriConstants.GET_ALL_AUTO_SHOPS_PAGINATION)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<Map<String, Object>> getAllAutoShopsPagination(
			@RequestParam(required = false) String autoShopName, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return autoShopService.getAllAutoShopsPagination(autoShopName, page, size);
	}

	@GetMapping(value = UriConstants.GET_AUTO_SHOP_BY_ID)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopSearchResponse getAutoRepairShopInfoById(@RequestBody @Valid SearchByIntRequest request) {
		AutoShopSearchResponse response = autoShopService.findAutoRepairShopById(request);
		return response;
	} 

	@GetMapping(value = UriConstants.GET_AUTO_SHOP_BY_NAME)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopSearchResponse getAutoRepairShopByName(@RequestBody @Valid SearchByNameRequest request) {
		AutoShopSearchResponse response = autoShopService.findAutoRepairShopByName(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_SHOP_BY_ZIP)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopListResponse getAutoRepairShopByZip(@RequestBody @Valid SearchByZipRequest request) {
		AutoShopListResponse response = autoShopService.findAutoRepairShopByZip(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_SHOP_COUNT)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public long getCountOfAllRepairShops() {
		return autoShopService.getCountOfAutoRepairShops();
	}

	@PostMapping(value = UriConstants.ADD_AUTO_SHOP)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopAddUpdateResponse addAutoRepairShopInformation(@RequestBody @Valid AutoShopAddUpdateRequest request) throws DuplicateAutoShopException {
		AutoShopAddUpdateResponse response = autoShopService.addAutoRepairShopInfo(request);
		return response;
	}

	@PutMapping(value = UriConstants.UPDATE_AUTO_SHOP)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopAddUpdateResponse updateAutoRepairShopInformation(@RequestBody @Valid AutoShopAddUpdateRequest request) {
		AutoShopAddUpdateResponse response = autoShopService.updateAutoShopInfo(request);
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_AUTO_SHOP)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoShopSearchResponse deleteAutoShopInformationById(@RequestBody @Valid SearchByIntRequest request) {
		AutoShopSearchResponse response = autoShopService.deleteAutoRepairShopInfo(request);
		return response;
	}

	@DeleteMapping(UriConstants.DELETE_ALL_AUTO_SHOPS)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public DeleteAllResponse deleteAllAutoRepairShopInformation() {
		DeleteAllResponse response =  autoShopService.deleteAllAutoShops();
		return response;
	}
}
