package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateContactException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Person;

public interface ContactDao {

	public List<Person> findAllPersonInformation() throws SecurityException, IOException;

	public ResponseEntity<Person> findPersonById(int personid)
			throws ResourceNotFoundException, SecurityException, IOException;

	public ResponseEntity<Person> findPersonByLastName(String lastname);

	public ResponseEntity<Person> findPersonByEmail(String email);

	public ResponseEntity<Person> findPersonByPhone(String phone);

	public long getCountOfPersonInformation();

	public int addPersonInformation(Person person) throws SecurityException, IOException, DuplicateContactException;

	public int updatePersonInformation(int personId, Person personDetails)
			throws SecurityException, IOException;

	public int deletePersonInformation(int personid) throws SecurityException, IOException;

	public int deleteAllPersonInformation();
}
