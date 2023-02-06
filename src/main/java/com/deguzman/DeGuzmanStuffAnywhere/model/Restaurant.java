package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int restaurant_id;
	
	@NotNull(message = "Invalid request, name field is missing/null")
	@Pattern(regexp = "[a-zA-Z,0-9]{0,50}", message = "Bad request, name field can only contain letters, special characters are not allowed")
	public String name;
	
	@NotNull(message = "Invalid request, address field is missing/null")
	@Pattern(regexp = "[a-zA-Z,0-9]{0,50}", message = "Bad request, address field can only contain letters, special characters are not allowed")
	public String address;
	
	@NotNull(message = "Invalid request, city field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Bad request, city field can only contain letters, special characters are not allowed")
	public String city;
	
	@NotNull(message = "Invalid request, state field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Bad request, state field can only contain letters, special characters are not allowed")
	public String state;
	
	@NotNull(message = "Invalid request, zip field is missing/null")
	@Pattern(regexp = "[0-9]{5}", message = "Bad request, zip field can only contain letters, special characters are not allowed")
	public String zip;
	
	@NotNull(message = "Invalid request, restaurant_type_id field is missing/null")
	@Pattern(regexp = "[0-9]{0,50}", message = "Bad request, restaurant_type_id field can only contain letters, special characters are not allowed")
	public int restaurant_type_id;
	
	public int getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int getRestaurant_type_id() {
		return restaurant_type_id;
	}
	public void setRestaurant_type_id(int restaurant_type_id) {
		this.restaurant_type_id = restaurant_type_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + restaurant_id;
		result = prime * result + restaurant_type_id;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		Restaurant other = (Restaurant) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (restaurant_id != other.restaurant_id)
			return false;
		if (restaurant_type_id != other.restaurant_type_id)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurant_id=" + restaurant_id + ", name=" + name + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", restaurant_type_id=" + restaurant_type_id + "]";
	}
	public Restaurant(int restaurant_id, String name, String address, String city, String state, String zip,
			int restaurant_type_id) {
		super();
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.restaurant_type_id = restaurant_type_id;
	}
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

}
