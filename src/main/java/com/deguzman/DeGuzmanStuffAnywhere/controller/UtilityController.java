package com.deguzman.DeGuzmanStuffAnywhere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UtilityListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UtilitySearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.model.Utility;
import com.deguzman.DeGuzmanStuffAnywhere.service.UtilityService;

@RestController
@RequestMapping("/app/utility-information")
@CrossOrigin
public class UtilityController {

	@Autowired
	private UtilityService utilityService;

	@GetMapping("/all")
	@CrossOrigin
	public UtilityListResponse getAllUtilityInformation() {
		UtilityListResponse response = utilityService.findAllUtilityInformation();
		return response;
	}

	@GetMapping("/dueDate")
	@CrossOrigin
	public UtilityListResponse getUtilityInformationByDueDate(@PathVariable String dueDate) {
		UtilityListResponse response = utilityService.findUtilityInformationByDueDate(dueDate);
		return response;
	}

	@GetMapping("/utility/{utility_id}")
	@CrossOrigin
	public UtilitySearchResponse getUtilityInformationById(@PathVariable long utility_id) {
		UtilitySearchResponse response = utilityService.findUtilityInformationById(utility_id);
		return response;
	}

	@GetMapping("/utility/name/{name}")
	@CrossOrigin
	public UtilitySearchResponse getUtilityInformationByName(@PathVariable String name) {
		UtilitySearchResponse response = utilityService.findUtilityInformationByName(name);
		return response;
	}

	@GetMapping("/utility/utility-type/{utility_type_id}")
	@CrossOrigin
	public UtilitySearchResponse getUtilityInformationByType(@PathVariable int utility_type_id) {
		UtilitySearchResponse response = utilityService.findUtilityInformationByType(utility_type_id);
		return response;
	}

	@GetMapping("/get-utility-count")
	@CrossOrigin
	public long getCountOfUtilities() {
		return utilityService.findUtilityCount();
	}

	@GetMapping("/add-utility-information")
	@CrossOrigin
	public int addUtilityInformation(@RequestBody Utility utility) {
		return utilityService.addUtilityInformation(utility);
	}

	@DeleteMapping("/utility/{utility_id}")
	@CrossOrigin
	public int deleteUtilityInformationById(@PathVariable long utility_id) {
		return utilityService.deleteUtilityInformation(utility_id);
	}

	@DeleteMapping("/delete-all-utilties")
	@CrossOrigin
	public DeleteAllResponse deleteAllUtilityInformation() {
		DeleteAllResponse response = utilityService.deleteAllUtilityInformation();
		return response;
	}
}
