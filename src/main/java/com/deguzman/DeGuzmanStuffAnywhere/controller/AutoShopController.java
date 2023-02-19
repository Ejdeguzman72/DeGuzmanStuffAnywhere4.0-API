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

import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop;
import com.deguzman.DeGuzmanStuffAnywhere.service.AutoRepairShopService;

@RestController
@RequestMapping("/app/auto-repair-shops")
@CrossOrigin
public class AutoShopController {

	@Autowired
	private AutoRepairShopService autoShopService;

	@GetMapping(value = UriConstants.GET_ALL_AUTO_SHOP)
	@CrossOrigin
	public AutoShopListResponse getAllAutoRepairShopInformation() {
		AutoShopListResponse response = autoShopService.findAllAutoRepairShopInfo();
		return response;
	}

	@GetMapping(value = UriConstants.GET_ALL_AUTO_SHOPS_PAGINATION)
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllAutoShopsPagination(
			@RequestParam(required = false) String autoShopName, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return autoShopService.getAllAutoShopsPagination(autoShopName, page, size);
	}

	@GetMapping("/repair-shop/{auto_shop_id}")
	@CrossOrigin
	public AutoShopSearchResponse getAutoRepairShopInfoById(@PathVariable int auto_shop_id) {
		AutoShopSearchResponse response = autoShopService.findAutoRepairShopById(auto_shop_id);
		return response;
	} 

	@GetMapping("/repair-shop/name/{autoShopName}")
	@CrossOrigin
	public AutoShopSearchResponse getAutoRepairShopByName(@PathVariable String autoShopName) {
		AutoShopSearchResponse response = autoShopService.findAutoRepairShopByName(autoShopName);
		return response;
	}

	@GetMapping("/repair-shop/zip/{zip}")
	@CrossOrigin
	public AutoShopListResponse getAutoRepairShopByZip(@PathVariable String zip) {
		AutoShopListResponse response = autoShopService.findAutoRepairShopByZip(zip);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_SHOP_COUNT)
	@CrossOrigin
	public long getCountOfAllRepairShops() {
		return autoShopService.getCountOfAutoRepairShops();
	}

	@PostMapping(value = UriConstants.ADD_AUTO_SHOP)
	@CrossOrigin
	public AutoShopAddUpdateResponse addAutoRepairShopInformation(@RequestBody @Valid AutoShopAddUpdateRequest request) throws DuplicateAutoShopException {
		AutoShopAddUpdateResponse response = autoShopService.addAutoRepairShopInfo(request);
		return response;
	}

	@PutMapping("/repair-shop/{auto_shop_id}")
	@CrossOrigin
	public AutoShopAddUpdateResponse updateAutoRepairShopInformation(@RequestBody @Valid AutoShopAddUpdateRequest request) {
		AutoShopAddUpdateResponse response = autoShopService.updateAutoShopInfo(request);
		return response;
	}

	@DeleteMapping("/repair-shop/{auto_shop_id}")
	@CrossOrigin
	public int deleteAutoShopInformationById(@PathVariable int auto_shop_id) {
		return autoShopService.deleteAutoRepairShopInfo(auto_shop_id);
	}

	@DeleteMapping(UriConstants.DELETE_ALL_AUTO_SHOPS)
	@CrossOrigin
	public DeleteAllResponse deleteAllAutoRepairShopInformation() {
		DeleteAllResponse response =  autoShopService.deleteAllAutoShops();
		return response;
	}
}
