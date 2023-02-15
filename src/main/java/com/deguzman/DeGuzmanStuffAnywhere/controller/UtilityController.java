package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.UtilityDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UtilityListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;
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
	public ResponseEntity<UtilityInfoDTO> getUtilityInformationById(@PathVariable long utility_id) {
		return utilityService.findUtilityInformationById(utility_id);
	}

	@GetMapping("/utility/name/{name}")
	@CrossOrigin
	public ResponseEntity<UtilityInfoDTO> getUtilityInformationByName(@PathVariable String name) {
		return utilityService.findUtilityInformationByName(name);
	}

	@GetMapping("/utility/utility-type/{utility_type_id}")
	@CrossOrigin
	public ResponseEntity<UtilityInfoDTO> getUtilityInformationByType(@PathVariable int utility_type_id) {
		return utilityService.findUtilityInformationByType(utility_type_id);
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
	public int deleteAllUtilityInformation() {
		return utilityService.deleteAllUtilityInformation();
	}
}
