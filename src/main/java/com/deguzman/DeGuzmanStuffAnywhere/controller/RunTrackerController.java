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
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerDTOSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.RunTrackerSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.service.RunTrackerService;

@RestController
@RequestMapping("/app/run-tracker-app")
@CrossOrigin
public class RunTrackerController {
	
	@Autowired
	private RunTrackerService runTrackerService;

	@GetMapping("/all")
	@CrossOrigin
	public RunTrackerListResponse getAllRunTrackerInformation() {
		RunTrackerListResponse response = runTrackerService.findAllRunTrackerInformation();
		return response;
	}
	
	@GetMapping("all-runs")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllRunInfoPagination(@RequestParam(required = false) String runDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return runTrackerService.getAllRunInfoPagination(runDate, page, size);
	}

	@GetMapping("/run/user/{user_id}")
	@CrossOrigin
	public RunTrackerListResponse getRunTrackerInformationByUser(@RequestBody @Valid SearchByLongRequest request) {
		RunTrackerListResponse response = runTrackerService.findRunTrackerInformationByUser(request);
		return response;
	}

	@GetMapping("/run/{run_id}")
	@CrossOrigin
	public RunTrackerSearchResponse getRunTrackerInformationById(@RequestBody @Valid SearchByLongRequest request) {
		RunTrackerSearchResponse response = runTrackerService.findRunTrackerById(request);
		return response;
	}
	
	@GetMapping("/run-dto/{run_id}")
	@CrossOrigin
	public RunTrackerDTOSearchResponse getRunTrackerInformationDTOById(@RequestBody @Valid SearchByLongRequest request) {
		RunTrackerDTOSearchResponse response = runTrackerService.findRunTrackerInformationDTOById(request);
		return response;
	}

	@GetMapping("run-count")
	@CrossOrigin
	public long getRunCount() {
		return runTrackerService.findCountOfRunTrackerInformation();
	}

	@PostMapping("/add-run-tracker-info")
	@CrossOrigin
	public RunTrackerAddUpdateResponse addRunTrackerInformation(@RequestBody RunTrackerAddUpdateRequest request) {
		RunTrackerAddUpdateResponse response = runTrackerService.addRunTrackerInfomration(request);
		return response;
	}

	@PutMapping("/run/{run_id}")
	@CrossOrigin
	public RunTrackerAddUpdateResponse updateRunTrackerInformation(@RequestBody @Valid RunTrackerAddUpdateRequest request) {
		RunTrackerAddUpdateResponse response = runTrackerService.updateRunTrackerInformation(request);
		return response;
	}
	
	@DeleteMapping("/run/{run_id}")
	@CrossOrigin
	public int deleteRunTrackerInformationById(@RequestBody @Valid SearchByLongRequest request) {
		return runTrackerService.deleteRunTrackerInformation(request);
	}

	@DeleteMapping("/delete-all-runs")
	@CrossOrigin
	public DeleteAllResponse deleteAllRunInformation() {
		DeleteAllResponse response = runTrackerService.deleteAllRunTrackerInformation();
		return response;
	}
}
