package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;
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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.AutoRepairShopDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop;
import com.deguzman.DeGuzmanStuffAnywhere.service.AutoRepairShopService;

@RestController
@RequestMapping("/app/auto-repair-shops")
@CrossOrigin
public class AutoShopController {

	@Autowired
	private AutoRepairShopService autoShopService;

	@GetMapping("/all")
	@CrossOrigin
	public AutoShopListResponse getAllAutoRepairShopInformation() {
		AutoShopListResponse response = autoShopService.findAllAutoRepairShopInfo();
		return response;
	}

	@GetMapping("/all-shops")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllAutoShopsPagination(
			@RequestParam(required = false) String autoShopName, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return autoShopService.getAllAutoShopsPagination(autoShopName, page, size);
	}

	@GetMapping("/repair-shop/{auto_shop_id}")
	@CrossOrigin
	public ResponseEntity<AutoRepairShop> getAutoRepairShopInfoById(@PathVariable int auto_shop_id) {
		return autoShopService.findAutoRepairShopById(auto_shop_id);
	}

	@GetMapping("/repair-shop/name/{autoShopName}")
	@CrossOrigin
	public ResponseEntity<AutoRepairShop> getAutoRepairShopByName(@PathVariable String autoShopName) {
		return autoShopService.findAutoRepairShopByName(autoShopName);
	}

	@GetMapping("/repair-shop/zip/{zip}")
	@CrossOrigin
	public AutoShopListResponse getAutoRepairShopByZip(@PathVariable String zip) {
		AutoShopListResponse response = autoShopService.findAutoRepairShopByZip(zip);
		return response;
	}

	@GetMapping("/repair-shop/count")
	@CrossOrigin
	public long getCountOfAllRepairShops() {
		return autoShopService.getCountOfAutoRepairShops();
	}

	@PostMapping("/add-auto-shop")
	@CrossOrigin
	public int addAutoRepairShopInformation(@RequestBody @Valid AutoRepairShop autoShop) throws DuplicateAutoShopException {
		return autoShopService.addAutoRepairShopInfo(autoShop);
	}

	@PutMapping("/repair-shop/{auto_shop_id}")
	@CrossOrigin
	public int updateAutoRepairShopInformation(@PathVariable int auto_shop_id,
			@RequestBody AutoRepairShop autoRepairShop) {
		return autoShopService.updateAutoShopInfo(auto_shop_id, autoRepairShop);
	}

	@DeleteMapping("/repair-shop/{auto_shop_id}")
	@CrossOrigin
	public int deleteAutoShopInformationById(@PathVariable int auto_shop_id) {
		return autoShopService.deleteAutoRepairShopInfo(auto_shop_id);
	}

	@DeleteMapping("/delete-all")
	@CrossOrigin
	public int deleteAllAutoRepairShopInformation() {
		return autoShopService.deleteAllAutoShops();
	}
}
