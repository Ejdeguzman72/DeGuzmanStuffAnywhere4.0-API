package com.deguzman.DeGuzmanStuffAnywhere.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class UtilityType {

	public int utility_type_id;
	public String utility_type_descr;

	public int getUtility_type_id() {
		return utility_type_id;
	}

	public void setUtility_type_id(int utility_type_id) {
		this.utility_type_id = utility_type_id;
	}

	public String getUtility_type_descr() {
		return utility_type_descr;
	}

	public void setUtility_type_descr(String utility_type_descr) {
		this.utility_type_descr = utility_type_descr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utility_type_descr == null) ? 0 : utility_type_descr.hashCode());
		result = prime * result + utility_type_id;
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
		UtilityType other = (UtilityType) obj;
		if (utility_type_descr == null) {
			if (other.utility_type_descr != null)
				return false;
		} else if (!utility_type_descr.equals(other.utility_type_descr))
			return false;
		if (utility_type_id != other.utility_type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UtilityType [utility_type_id=" + utility_type_id + ", utility_type_descr=" + utility_type_descr + "]";
	}

	public UtilityType(int utility_type_id, String utility_type_descr) {
		super();
		this.utility_type_id = utility_type_id;
		this.utility_type_descr = utility_type_descr;
	}

	public UtilityType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
