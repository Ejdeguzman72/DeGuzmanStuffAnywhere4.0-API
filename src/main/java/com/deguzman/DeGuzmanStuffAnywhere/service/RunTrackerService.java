package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.RunTrackerDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerDTOSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
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
	
	public RunTrackerListResponse findRunTrackerInformationByUser(@RequestBody @Valid SearchByLongRequest request) {
		RunTrackerListResponse response = new RunTrackerListResponse();
		List<RunTrackerInfoDTO> list = runTrackerDaoImpl.findRunTrackerInformationByUser(request.getId());
		
		response.setList(list);
		return response;
	}
	
	public RunTrackerDTOSearchResponse findRunTrackerInformationDTOById(@RequestBody @Valid SearchByLongRequest request) {
		RunTrackerDTOSearchResponse response = new RunTrackerDTOSearchResponse();
		RunTrackerInfoDTO run = runTrackerDaoImpl.findRunTrackerInformationDTOById(request.getId());
		
		response.setRun(run);
		return response;
	}
	
	public RunTrackerSearchResponse findRunTrackerById(@RequestBody @Valid SearchByLongRequest request) {
		RunTrackerSearchResponse response = new RunTrackerSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker run = runTrackerDaoImpl.findRunTrackerById(request.getId());
		
		response.setRun(run);
		return response;
	}
	
	public long findCountOfRunTrackerInformation() {
		return runTrackerDaoImpl.findCountOfRunTrackerInformation();
	}
	
	public RunTrackerAddUpdateResponse addRunTrackerInfomration(RunTrackerAddUpdateRequest request) {
		RunTrackerAddUpdateResponse response = new RunTrackerAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker runTracker = null;
		int count = 0;
		
		count = runTrackerDaoImpl.addRunTrackerInformation(request);
		if (count > 0) {
			runTracker.setRunDate(request.getRunDate());
			runTracker.setRunDistance(request.getRunDistance());
			runTracker.setRunTime(request.getRunTime());
			runTracker.setUser_id(request.getUser_id());
			if (runTracker != null) {
				response.setRunTracker(runTracker);
			}
		}
		
		return response;
	}
	
	public RunTrackerAddUpdateResponse updateRunTrackerInformation(RunTrackerAddUpdateRequest request) {
		RunTrackerAddUpdateResponse response = new RunTrackerAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker runTracker = runTrackerDaoImpl.findRunTrackerById(request.getRun_id());
		int count = 0;
		
		count = runTrackerDaoImpl.updateRunTrackerInformation(request.getRun_id(),request);
		if (count > 0) {
			runTracker.setRunDate(request.getRunDate());
			runTracker.setRunDistance(request.getRunDistance());
			runTracker.setRunTime(request.getRunTime());
			runTracker.setUser_id(request.getUser_id());
			if (runTracker != null) {
				response.setRunTracker(runTracker);
			}
		}
		
		return response;
	}
	
	public int deleteRunTrackerInformation(@RequestBody @Valid SearchByLongRequest request) {
		return runTrackerDaoImpl.deleteRunTrackerInformation(request.getId());
	}
	
	public DeleteAllResponse deleteAllRunTrackerInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = runTrackerDaoImpl.deleteAllRunTrackerInformation();
		
		response.setCount(count);
		return response;
	}
}
