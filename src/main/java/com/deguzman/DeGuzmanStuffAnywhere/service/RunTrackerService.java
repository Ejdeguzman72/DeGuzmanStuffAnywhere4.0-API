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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.RunTrackerDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.RunTrackerJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.RunTracker;

@Service
public class RunTrackerService {

	@Autowired
	private RunTrackerDaoImpl runTrackerDaoImpl;
	
	@Autowired
	private RunTrackerJpaDao runTrackerDao;
	
	public RunTrackerListResponse findAllRunTrackerInformation() {
		RunTrackerListResponse response = new RunTrackerListResponse();
		List<RunTrackerInfoDTO> list = runTrackerDaoImpl.findAllRunTrackerInformation();
		
		response.setList(list);
		return response;
	}
	
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
	
	public RunTrackerListResponse findRunTrackerInformationByUser(@PathVariable long user_id) {
		RunTrackerListResponse response = new RunTrackerListResponse();
		List<RunTrackerInfoDTO> list = runTrackerDaoImpl.findRunTrackerInformationByUser(user_id);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<RunTrackerInfoDTO> findRunTrackerInformationDTOById(@PathVariable long run_id) {
		return runTrackerDaoImpl.findRunTrackerInformationDTOById(run_id);
	}
	
	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker> findRunTrackerById(@PathVariable long run_id) {
		return runTrackerDaoImpl.findRunTrackerById(run_id);
	}
	
	public long findCountOfRunTrackerInformation() {
		return runTrackerDaoImpl.findCountOfRunTrackerInformation();
	}
	
	public int addRunTrackerInfomration(com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker runTracker) {
		return runTrackerDaoImpl.addRunTrackerInformation(runTracker);
	}
	
	public int updateRunTrackerInformation(long run_id, com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker runTrackerDetails) {
		return runTrackerDaoImpl.updateRunTrackerInformation(run_id, runTrackerDetails);
	}
	
	public int deleteRunTrackerInformation(@PathVariable long run_id) {
		return runTrackerDaoImpl.deleteRunTrackerInformation(run_id);
	}
	
	public int deleteAllRunTrackerInformation() {
		return runTrackerDaoImpl.deleteAllRunTrackerInformation();
	}
}
