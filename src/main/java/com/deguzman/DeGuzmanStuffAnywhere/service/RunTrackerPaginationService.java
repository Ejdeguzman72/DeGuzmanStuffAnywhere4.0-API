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

import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.RunTrackerJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.RunTracker;

@Service
public class RunTrackerPaginationService {

	@Autowired
	private RunTrackerJpaDao runTrackerDao;
	
	public ResponseEntity<Map<String, Object>> getAllRunInfoPagination(@RequestParam(required = false) String runDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<RunTracker> shop = runTrackerDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<RunTracker> pageBooks = null;

			if (runDate == null) {
				pageBooks = runTrackerDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("runs", shop);
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
}
