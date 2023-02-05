package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class RestaurantType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int restaurant_type_id;
	public String descr;

	public int getRestaurant_type_id() {
		return restaurant_type_id;
	}

	public void setRestaurant_type_id(int restaurant_type_id) {
		this.restaurant_type_id = restaurant_type_id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + restaurant_type_id;
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
		RestaurantType other = (RestaurantType) obj;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (restaurant_type_id != other.restaurant_type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestaurantType [restaurant_type_id=" + restaurant_type_id + ", descr=" + descr + "]";
	}

	public RestaurantType(int restaurant_type_id, String descr) {
		super();
		this.restaurant_type_id = restaurant_type_id;
		this.descr = descr;
	}

	public RestaurantType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
