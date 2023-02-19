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
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateContactException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Person;
import com.deguzman.DeGuzmanStuffAnywhere.service.ContactService;

@RestController
@RequestMapping("/app/person-info")
@CrossOrigin
public class ContactInfoController {

	@Autowired
	private ContactService contactInfoService;

	@GetMapping("/all")
	@CrossOrigin
	public ContactListResponse getAllPersonInfo() throws SecurityException, IOException {
		ContactListResponse response = contactInfoService.findAllPersonInformation();
		return response;
	}

	@GetMapping("/all-contacts")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllPersonsPagination(@RequestParam(required = false) String firstname,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return contactInfoService.getAllPersonsPagination(firstname, page, size);
	}

	@GetMapping("/person/{personId}")
	@CrossOrigin
	public ResponseEntity<Person> getPersonInformationById(@PathVariable int personId)
			throws ResourceNotFoundException, SecurityException, IOException {
		return contactInfoService.findPersonById(personId);
	}

	@GetMapping("/person/lastname/{lastname}")
	@CrossOrigin
	public ResponseEntity<Person> getPersonInformationByLastname(@PathVariable String lastname) {
		return contactInfoService.findPersonByLastname(lastname);
	}

	@GetMapping("/person/email/{email}")
	@CrossOrigin
	public ResponseEntity<Person> getPersonInformationByEmail(@PathVariable String email) {
		return contactInfoService.findPersonByEmail(email);
	}

	@GetMapping("/person/phone/{phone}")
	@CrossOrigin
	public ResponseEntity<Person> getPersonInformationByPhone(@PathVariable String phone) {
		return contactInfoService.findPersonByPhone(phone);
	}

	@GetMapping("/count")
	@CrossOrigin
	public long getCountOfPersonInfo() {
		return contactInfoService.getCountofPersonInformation();
	}

	@PostMapping("/add-person-information")
	@CrossOrigin
	public ContactAddUpdateResponse saveContactInformation(@RequestBody @Valid ContactAddUpdateRequest request) throws SecurityException, IOException, DuplicateContactException {
		ContactAddUpdateResponse response = contactInfoService.addPersonInformation(request);
		return response;
	}
	
	@PutMapping("/person/{personId}")
	@CrossOrigin
	public ContactAddUpdateResponse updateContactInformation(@RequestBody @Valid ContactAddUpdateRequest request) throws SecurityException, IOException {
		ContactAddUpdateResponse response = contactInfoService.updatePersonInformation(request);
		return response;
	}

	@DeleteMapping("/delete-all")
	@CrossOrigin
	public DeleteAllResponse deleteAllContactInformation() {
		DeleteAllResponse response = contactInfoService.deleteAllPersonInformation();
		return response;
	}

	@DeleteMapping("/person/{personId}")
	@CrossOrigin
	public int deleteContactInformation(@PathVariable int personId) throws SecurityException, IOException {
		return contactInfoService.deletePersonInformation(personId);
	}
}
