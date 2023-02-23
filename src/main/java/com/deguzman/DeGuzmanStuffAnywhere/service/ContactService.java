package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.ContactDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByEmail;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByPhone;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateContactException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.PersonJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Person;

@Service
public class ContactService {

	@Autowired
	private ContactDaoImpl contactDaoImpl;

	@Autowired
	private PersonJpaDao personJpaDao;

	public ContactListResponse findAllPersonInformation() throws IOException {
		ContactListResponse response = new ContactListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Person> list = contactDaoImpl.findAllPersonInformation();

		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllPersonsPagination(@RequestParam(required = false) String firstname,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<Person> shop = personJpaDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Person> pageBooks = null;

			if (firstname == null) {
				pageBooks = personJpaDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("persons", shop);
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

	public ContactSearchResponse findPersonById(@Valid SearchByIntRequest request)
			throws ResourceNotFoundException, SecurityException, IOException {
		ContactSearchResponse response = new ContactSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = contactDaoImpl.findPersonById(request.getId());
		
		response.setPerson(person);
		return response;
	}

	public ContactSearchResponse findPersonByLastname(SearchByNameRequest request) {
		ContactSearchResponse response = new ContactSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = contactDaoImpl.findPersonByLastName(request.getName());
		
		response.setPerson(person);
		return response;
	}

	public ContactSearchResponse findPersonByEmail(SearchByPhone request) {
		ContactSearchResponse response = new ContactSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = contactDaoImpl.findPersonByEmail(request.getPhone());
		
		response.setPerson(person);
		return response;
	}

	public ContactSearchResponse findPersonByPhone(SearchByEmail request) {
		ContactSearchResponse response = new ContactSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = contactDaoImpl.findPersonByPhone(request.getEmail());
		
		response.setPerson(person);
		return response;
	}

	public long getCountofPersonInformation() {
		return contactDaoImpl.getCountOfPersonInformation();
	}

	public ContactAddUpdateResponse addPersonInformation(ContactAddUpdateRequest request)
			throws SecurityException, IOException, DuplicateContactException {
		ContactAddUpdateResponse response = new ContactAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = null;
		int count = 0;

		count = contactDaoImpl.addPersonInformation(request);
		if (count != 0) {
			person.setFirstname(request.getFirstname());
			person.setMiddleInitial(request.getMiddleInitial());
			person.setLastname(request.getLastname());
			person.setAddress01(request.getAddress01());
			person.setAddress02(request.getAddress02());
			person.setCity(request.getCity());
			person.setState(request.getState());
			person.setZipcode(request.getZipcode());
			person.setBirthdate(request.getBirthdate());
			person.setEmail(request.getEmail());
			person.setPhone(request.getPhone());
			if (person != null) {
				response.setPerson(person);
			}
		}

		return response;
	}

	public ContactAddUpdateResponse updatePersonInformation(ContactAddUpdateRequest request)
			throws SecurityException, IOException {
		ContactAddUpdateResponse response = new ContactAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = null;
		int count = 0;

		count = contactDaoImpl.updatePersonInformation(request.getPersonId(), request);
		if (count > 0) {
			person.setFirstname(request.getFirstname());
			person.setMiddleInitial(request.getMiddleInitial());
			person.setLastname(request.getLastname());
			person.setAddress01(request.getAddress01());
			person.setAddress02(request.getAddress02());
			person.setCity(request.getCity());
			person.setState(request.getState());
			person.setZipcode(request.getZipcode());
			person.setBirthdate(request.getBirthdate());
			person.setEmail(request.getEmail());
			person.setPhone(request.getPhone());
			if (person != null) {
				response.setPerson(person);
			}
		}

		return response;
	}

	public ContactSearchResponse deletePersonInformation(SearchByIntRequest request) throws SecurityException, IOException, ResourceNotFoundException {
		ContactSearchResponse response = new ContactSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Person person = contactDaoImpl.findPersonById(request.getId());
		int count = 0;
		
		count = contactDaoImpl.deletePersonInformation(request.getId());
		if (count > 0) {
			response.setPerson(person);
		}
		
		return response;
	}

	public DeleteAllResponse deleteAllPersonInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = contactDaoImpl.deleteAllPersonInformation();

		response.setCount(count);

		return response;
	}
}
