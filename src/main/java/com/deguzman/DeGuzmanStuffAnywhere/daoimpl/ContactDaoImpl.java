package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dao.ContactDao;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateContactException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Person;

@Repository
public class ContactDaoImpl implements ContactDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Logger LOGGER = LoggerFactory.getLogger(ContactDaoImpl.class);

	String GET_ALL_CONTACT_INFO = "SELECT * FROM PERSON ORDER BY FIRSTNAME, LASTNAME";
	String GET_CONTACT_INFO_BY_ID = "SELECT * FROM PERSON WHERE PERSON_ID = ?";
	String GET_CONTACT_INFO_BY_LASTNAME = "SELECT * FROM PERSON WHERE LASTNAME = ?";
	String GET_CONTACT_INFO_BY_EMAIL = "SELECT * FROM PERSON WHERE EMAIL = ?";
	String GET_CONTACT_INFO_BY_PHONE = "SELECT * FROM PERSON WHERE PHONE = ?";
	String GET_COUNT_OF_PERSON_INFO = "SELECT COUNT(*) FROM PERSON";
	String INSERT_NEW_CONTACT_INFO = "INSERT INTO PERSON "
			+ "(FIRSTNAME, MIDDLE_INITIAL, LASTNAME, ADDRESS01, ADDRESS02, CITY, STATE, ZIPCODE, AGE, BIRTHDATE, EMAIL, PHONE) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String UPDATE_CONTACT_INFO = "UPDATE PERSON "
			+ "SET FIRSTNAME = ?, "
			+ "MIDDLE_INITIAL = ?, "
			+ "LASTNAME = ?, "
			+ "ADDRESS01 = ?, "
			+ "ADDRESS02 = ?, "
			+ "CITY = ?, "
			+ "STATE = ?, "
			+ "ZIPCODE = ?, "
			+ "AGE = ?, "
			+ "BIRTHDATE = ?, "
			+ "PHONE = ?, "
			+ "EMAIL = ? "
			+ "WHERE PERSON_ID = ?";
	String DELETE_CONTACT_INFO_BY_ID = "DELETE FROM PERSON WHERE PERSON_ID = ?";
	String DELETE_ALL_CONTACT_INFO = "DELETE FROM PERSON";

	@Override
	@Cacheable(value = "contactList")
	public List<Person> findAllPersonInformation() throws SecurityException, IOException {
		List<Person> personList = new ArrayList<>();
		try {
			personList = jdbcTemplate.query(GET_ALL_CONTACT_INFO,
					BeanPropertyRowMapper.newInstance(Person.class));
			
			LOGGER.info("Retrieved all persons...");			
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return personList;
	}

	@Override
	@Cacheable(value = "contactById", key = "#personId")
	public ResponseEntity<Person> findPersonById(int personId)
			throws ResourceNotFoundException, SecurityException, IOException {
		Person personInfo = new Person();
		try {
			personInfo = jdbcTemplate.queryForObject(GET_CONTACT_INFO_BY_ID,
					BeanPropertyRowMapper.newInstance(Person.class), personId);
			
			LOGGER.info("Retrieved Person Info: " + personInfo.getFirstname() + " " + personInfo.getLastname());			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return ResponseEntity.ok().body(personInfo);
	}

	@Override
	public ResponseEntity<Person> findPersonByLastName(String lastname) {
		Person person = new Person();
		try {
			person = jdbcTemplate.queryForObject(GET_CONTACT_INFO_BY_LASTNAME,
					BeanPropertyRowMapper.newInstance(Person.class), lastname);
			
			LOGGER.info("Retrieved Person with Last Name: " + lastname);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error: " + e.toString());
		}

		return ResponseEntity.ok().body(person);
	}

	@Override
	public ResponseEntity<Person> findPersonByEmail(String email) {
		Person person = new Person();
		
		try {
			person = jdbcTemplate.queryForObject(GET_CONTACT_INFO_BY_EMAIL,
					BeanPropertyRowMapper.newInstance(Person.class), email);

			LOGGER.info("Retrieved person with email: " + email);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return ResponseEntity.ok().body(person);
	}

	@Override
	public ResponseEntity<Person> findPersonByPhone(String phone) {
		Person person = new Person();
		
		try {
			person = jdbcTemplate.queryForObject(GET_CONTACT_INFO_BY_PHONE,
					BeanPropertyRowMapper.newInstance(Person.class), phone);
			
			LOGGER.info("Retrived person with phone: " + phone);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return ResponseEntity.ok().body(person);
	}

	@Override
	@CachePut(value = "contactList")
	public int addPersonInformation(Person person) throws SecurityException, IOException, DuplicateContactException {
		int result = 0;

		try {
			String firstname = person.getFirstname();
			String middleInitial = person.getMiddleInitial();
			String lastname = person.getLastname();
			String address01 = person.getAddress01();
			String address02 = person.getAddress02();
			String city = person.getCity();
			String state = person.getState();
			String zip = person.getZipcode();
			int age = person.getAge();
			String birthdate = person.getBirthdate();
			String phone = person.getPhone();
			String email = person.getEmail();
			
			if (checkDuplicateContact(phone)) {
				throw new DuplicateContactException("Phone Number already exists!");
			}

			LOGGER.info("Adding Person Information for " + firstname + " " + lastname);

			result = jdbcTemplate.update(INSERT_NEW_CONTACT_INFO, new Object[] { firstname, middleInitial, lastname,
					address01, address02, city, state, zip, age, birthdate, phone, email });
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "contactById", key = "#personId")
	public int updatePersonInformation(int personId, Person personDetails)
			throws SecurityException, IOException {

		int result = 0;

		Person person = new Person();
		
		try {
			person = jdbcTemplate.queryForObject(GET_CONTACT_INFO_BY_ID,
					BeanPropertyRowMapper.newInstance(Person.class), personId);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		try {
			if (person != null) {
				person.setFirstname(personDetails.getFirstname());
				person.setMiddleInitial(personDetails.getMiddleInitial());
				person.setLastname(personDetails.getLastname());
				person.setAddress01(personDetails.getAddress01());
				person.setAddress02(personDetails.getAddress02());
				person.setCity(personDetails.getCity());
				person.setState(personDetails.getState());
				person.setZipcode(personDetails.getZipcode());
				person.setAge(personDetails.getAge());
				person.setBirthdate(personDetails.getBirthdate());
				person.setPhone(personDetails.getPhone());
				person.setEmail(personDetails.getEmail());
				person.setPersonId(personDetails.getPersonId());;
				
				result = jdbcTemplate.update(UPDATE_CONTACT_INFO, new Object[] {
						person.getFirstname(),
						person.getMiddleInitial(),
						person.getLastname(),
						person.getAddress01(),
						person.getAddress02(),
						person.getCity(),
						person.getState(),
						person.getZipcode(),
						person.getAge(),
						person.getBirthdate(),
						person.getPhone(),
						person.getEmail(),
						person.getPersonId()
				});
				
				
				LOGGER.info("Updating person info with person_id: " + personId);
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return result;
	}

	@Override
	@CachePut(value = "contactById", key = "#personId")
	public int deletePersonInformation(int personId) throws SecurityException, IOException {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_CONTACT_INFO_BY_ID, personId);

			LOGGER.info("Deleting Person Information By ID: " + personId);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		return count;
	}

	@Override
	public long getCountOfPersonInformation() {
		long count = jdbcTemplate.queryForObject(GET_COUNT_OF_PERSON_INFO, Integer.class);

		LOGGER.info("Getting person count...");

		return count;
	}

	@Override
	@CachePut(value = "contactList")
	public int deleteAllPersonInformation() {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_ALL_CONTACT_INFO);

			LOGGER.info("Deleting All Person Information...");

		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return count;
	}
	
	public boolean checkDuplicateContact(String phone) throws SecurityException, IOException {
		
		List<Person> personList = findAllPersonInformation();
		List<String> phoneList;
		boolean result = false;
		
		phoneList = personList.stream().map(Person::getPhone).collect(Collectors.toList());
		
		if (phoneList.contains(phone)) {
			result = true;
		}
		
		return result;
	}
 
}
