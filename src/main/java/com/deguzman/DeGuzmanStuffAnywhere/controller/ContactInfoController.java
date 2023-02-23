package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.io.IOException;
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

import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByEmail;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByPhone;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateContactException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.service.ContactService;

@RestController
@CrossOrigin
public class ContactInfoController {

	@Autowired
	private ContactService contactInfoService;

	@GetMapping(value = UriConstants.GET_ALL_CONTACTS)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactListResponse getAllPersonInfo() throws SecurityException, IOException {
		ContactListResponse response = contactInfoService.findAllPersonInformation();
		return response;
	}

	@GetMapping(value = UriConstants.GET_ALL_CONTACTS_PAGINATION)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<Map<String, Object>> getAllPersonsPagination(@RequestParam(required = false) String firstname,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return contactInfoService.getAllPersonsPagination(firstname, page, size);
	}

	@GetMapping(value = UriConstants.GET_CONTACT_BY_ID)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactSearchResponse getPersonInformationById(@RequestBody @Valid SearchByIntRequest request)
			throws ResourceNotFoundException, SecurityException, IOException {
		ContactSearchResponse response = contactInfoService.findPersonById(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_CONTACT_BY_LASTNAME)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactSearchResponse getPersonInformationByLastname(@RequestBody @Valid SearchByNameRequest request) {
		ContactSearchResponse response = contactInfoService.findPersonByLastname(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_CONTACT_BY_EMAIL)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactSearchResponse getPersonInformationByEmail(@RequestBody @Valid SearchByPhone request) {
		ContactSearchResponse response = contactInfoService.findPersonByEmail(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_CONTACT_BY_EMAIL)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactSearchResponse getPersonInformationByPhone(@RequestBody @Valid SearchByEmail request) {
		ContactSearchResponse response = contactInfoService.findPersonByPhone(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_CONTACT_COUNT)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public long getCountOfPersonInfo() {
		return contactInfoService.getCountofPersonInformation();
	}

	@PostMapping(value = UriConstants.ADD_CONTACT_INFORMATON)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactAddUpdateResponse saveContactInformation(@RequestBody @Valid ContactAddUpdateRequest request) throws SecurityException, IOException, DuplicateContactException {
		ContactAddUpdateResponse response = contactInfoService.addPersonInformation(request);
		return response;
	}
	
	@PutMapping(value = UriConstants.UPDATE_CONTACT_INFORMATION)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactAddUpdateResponse updateContactInformation(@RequestBody @Valid ContactAddUpdateRequest request) throws SecurityException, IOException {
		ContactAddUpdateResponse response = contactInfoService.updatePersonInformation(request);
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_ALL_CONTACTS)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public DeleteAllResponse deleteAllContactInformation() {
		DeleteAllResponse response = contactInfoService.deleteAllPersonInformation();
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_CONTACT)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ContactSearchResponse deleteContactInformation(@RequestBody @Valid SearchByIntRequest request) throws SecurityException, IOException, ResourceNotFoundException {
		ContactSearchResponse response = contactInfoService.deletePersonInformation(request);
		return response;
	}
}
