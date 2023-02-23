package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.io.IOException;
import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateContactException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Person;

public interface ContactDao {

	public List<Person> findAllPersonInformation() throws SecurityException, IOException;

	public Person findPersonById(int personid)
			throws ResourceNotFoundException, SecurityException, IOException;

	public Person findPersonByLastName(String lastname);

	public Person findPersonByEmail(String email);

	public Person findPersonByPhone(String phone);

	public long getCountOfPersonInformation();

	public int addPersonInformation(Person person) throws SecurityException, IOException, DuplicateContactException;

	public int updatePersonInformation(int personId, Person personDetails)
			throws SecurityException, IOException;

	public int deletePersonInformation(int personid) throws SecurityException, IOException;

	public int deleteAllPersonInformation();
}
