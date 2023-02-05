package com.deguzman.DeGuzmanStuffAnywhere.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Person {

	public int personId;
	public String firstname;
	public String middleInitial;
	public String lastname;
	public int age;
	public String address01;
	public String address02;
	public String city;
	public String state;
	public String zipcode;
	public String phone;
	public String email;
	public String birthdate;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress01() {
		return address01;
	}

	public void setAddress01(String address01) {
		this.address01 = address01;
	}

	public String getAddress02() {
		return address02;
	}

	public void setAddress02(String address02) {
		this.address02 = address02;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address01 == null) ? 0 : address01.hashCode());
		result = prime * result + ((address02 == null) ? 0 : address02.hashCode());
		result = prime * result + age;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result + personId;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (address01 == null) {
			if (other.address01 != null)
				return false;
		} else if (!address01.equals(other.address01))
			return false;
		if (address02 == null) {
			if (other.address02 != null)
				return false;
		} else if (!address02.equals(other.address02))
			return false;
		if (age != other.age)
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (middleInitial == null) {
			if (other.middleInitial != null)
				return false;
		} else if (!middleInitial.equals(other.middleInitial))
			return false;
		if (personId != other.personId)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [person_id=" + personId + ", firstname=" + firstname + ", middleInitial=" + middleInitial
				+ ", lastname=" + lastname + ", age=" + age + ", address01=" + address01 + ", address02=" + address02
				+ ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", phone=" + phone + ", email="
				+ email + ", birthdate=" + birthdate + "]";
	}

	public Person(int person_id, String firstname, String middleInitial, String lastname, int age, String address01,
			String address02, String city, String state, String zipcode, String phone, String email, String birthdate) {
		super();
		this.personId = person_id;
		this.firstname = firstname;
		this.middleInitial = middleInitial;
		this.lastname = lastname;
		this.age = age;
		this.address01 = address01;
		this.address02 = address02;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String firstname, String middleInitial, String lastname, String address01, String address02,
			String city, String state, String zipcode, int age, String birthdate, String phone, String email) {
		super();
		this.firstname = firstname;
		this.middleInitial = middleInitial;
		this.lastname = lastname;
		this.age = age;
		this.address01 = address01;
		this.address02 = address02;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
	}

}
