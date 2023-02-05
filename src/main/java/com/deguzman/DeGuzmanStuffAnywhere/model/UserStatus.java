package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class UserStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -133071239717271640L;
	public int user_status_id;
	public String user_status_descr;

	public int getUser_status_id() {
		return user_status_id;
	}

	public void setUser_status_id(int user_status_id) {
		this.user_status_id = user_status_id;
	}

	public String getUser_status_descr() {
		return user_status_descr;
	}

	public void setUser_status_descr(String user_status_descr) {
		this.user_status_descr = user_status_descr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_status_descr == null) ? 0 : user_status_descr.hashCode());
		result = prime * result + user_status_id;
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
		UserStatus other = (UserStatus) obj;
		if (user_status_descr == null) {
			if (other.user_status_descr != null)
				return false;
		} else if (!user_status_descr.equals(other.user_status_descr))
			return false;
		if (user_status_id != other.user_status_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStatus [user_status_id=" + user_status_id + ", user_status_descr=" + user_status_descr + "]";
	}

	public UserStatus(int user_status_id, String user_status_descr) {
		super();
		this.user_status_id = user_status_id;
		this.user_status_descr = user_status_descr;
	}

	public UserStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

}
