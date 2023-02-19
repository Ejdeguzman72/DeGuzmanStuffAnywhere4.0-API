package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.io.IOException;
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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.ContactDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.ContactListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
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

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Person> findPersonById(int personId)
			throws ResourceNotFoundException, SecurityException, IOException {
		return contactDaoImpl.findPersonById(personId);
	}

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Person> findPersonByLastname(String lastname) {
		return contactDaoImpl.findPersonByLastName(lastname);
	}

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Person> findPersonByEmail(String email) {
		return contactDaoImpl.findPersonByEmail(email);
	}

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.Person> findPersonByPhone(String phone) {
		return contactDaoImpl.findPersonByPhone(phone);
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

	public int deletePersonInformation(int personId) throws SecurityException, IOException {
		return contactDaoImpl.deletePersonInformation(personId);
	}

	public DeleteAllResponse deleteAllPersonInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = contactDaoImpl.deleteAllPersonInformation();

		response.setCount(count);

		return response;
	}
}
