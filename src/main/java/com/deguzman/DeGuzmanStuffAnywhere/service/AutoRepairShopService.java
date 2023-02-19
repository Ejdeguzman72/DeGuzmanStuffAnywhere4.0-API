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
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.AutoRepairShopDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.AutoRepairShopJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.AutoRepairShop;

@Service
public class AutoRepairShopService {

	@Autowired
	private AutoRepairShopDaoImpl autoRepairShopDaoImpl;

	@Autowired
	private AutoRepairShopJpaDao autoShopDao;

	public AutoShopListResponse findAllAutoRepairShopInfo() {
		AutoShopListResponse response = new AutoShopListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop> list = autoRepairShopDaoImpl
				.findAllAutoRepairShopInfo();

		response.setList(list);

		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllAutoShopsPagination(
			@RequestParam(required = false) String autoShopname, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			List<AutoRepairShop> shop = autoShopDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<AutoRepairShop> pageBooks = null;

			if (autoShopname == null) {
				pageBooks = autoShopDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("autoShops", shop);
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

	public AutoShopSearchResponse findAutoRepairShopByName(
			String autoShopName) {
		AutoShopSearchResponse response = new AutoShopSearchResponse();
		
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop autoshop =  autoRepairShopDaoImpl.findAutoRepairShopByName(autoShopName);
		
		response.setAutoShop(autoshop);
		return response;
	}

	public AutoShopListResponse findAutoRepairShopByZip(String zip) {
		AutoShopListResponse response = new AutoShopListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop> list = autoRepairShopDaoImpl
				.findAutoRepairShopByZip(zip);

		response.setList(list);

		return response;
	}

	public long getCountOfAutoRepairShops() {
		return autoRepairShopDaoImpl.getCountOfAutoRepairShops();
	}

	public AutoShopAddUpdateResponse addAutoRepairShopInfo(AutoShopAddUpdateRequest request)
			throws DuplicateAutoShopException {
		AutoShopAddUpdateResponse response = new AutoShopAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop autoShop = new com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop();
		int count = 0;

		count = autoRepairShopDaoImpl.addAutoRepairShopInfo(request);
		if (count > 0) {
			autoShop.setAddress(request.getAddress());
			autoShop.setAutoShopName(request.getAutoShopName());
			autoShop.setCity(request.getCity());
			autoShop.setState(request.getState());
			autoShop.setZip(request.getZip());
		}

		response.setAutoShop(autoShop);
		return response;
	}

	public AutoShopAddUpdateResponse updateAutoShopInfo(AutoShopAddUpdateRequest request) {
		AutoShopAddUpdateResponse response = new AutoShopAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop autoShop = null;
		int count = 0;

		count = autoRepairShopDaoImpl.updateAutoShopInfo(request.getAuto_shop_id(), request);

		if (count > 0) {
			autoShop.setAddress(request.getAddress());
			autoShop.setAutoShopName(request.getAutoShopName());
			autoShop.setCity(request.getCity());
			autoShop.setState(request.getState());
			autoShop.setZip(request.getZip());
			if (autoShop != null) {
				response.setAutoShop(autoShop);
			}
		}

		return response;
	}

	public int deleteAutoRepairShopInfo(int auto_shop_id) {
		return autoRepairShopDaoImpl.deleteAutoRepairShopInfo(auto_shop_id);
	}

	public DeleteAllResponse deleteAllAutoShops() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = 0;

		count = autoRepairShopDaoImpl.deleteAllAutoShop();

		response.setCount(count);

		return response;
	}

	public com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop findAutoRepairShopById(
			int auto_shop_id) {
		return autoRepairShopDaoImpl.findAutoRepairShopById(auto_shop_id);
	}
}
