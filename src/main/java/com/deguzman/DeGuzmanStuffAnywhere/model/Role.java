package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666996380336338435L;
	public int role_id;
	public String role_descr;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_descr() {
		return role_descr;
	}

	public void setRole_descr(String role_descr) {
		this.role_descr = role_descr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role_descr == null) ? 0 : role_descr.hashCode());
		result = prime * result + role_id;
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
		Role other = (Role) obj;
		if (role_descr == null) {
			if (other.role_descr != null)
				return false;
		} else if (!role_descr.equals(other.role_descr))
			return false;
		if (role_id != other.role_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_descr=" + role_descr + "]";
	}

	public Role(int role_id, String role_descr) {
		super();
		this.role_id = role_id;
		this.role_descr = role_descr;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

}
