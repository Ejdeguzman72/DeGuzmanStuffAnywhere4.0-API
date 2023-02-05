package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class TransactionType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3284448543761009503L;
	public long transaction_type_id;
	public String transaction_type_descr;

	public long getTransaction_type_id() {
		return transaction_type_id;
	}

	public void setTransaction_type_id(long transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}

	public String getTransaction_type_descr() {
		return transaction_type_descr;
	}

	public void setTransaction_type_descr(String transaction_type_descr) {
		this.transaction_type_descr = transaction_type_descr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transaction_type_descr == null) ? 0 : transaction_type_descr.hashCode());
		result = prime * result + (int) (transaction_type_id ^ (transaction_type_id >>> 32));
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
		TransactionType other = (TransactionType) obj;
		if (transaction_type_descr == null) {
			if (other.transaction_type_descr != null)
				return false;
		} else if (!transaction_type_descr.equals(other.transaction_type_descr))
			return false;
		if (transaction_type_id != other.transaction_type_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransactionType [transaction_type_id=" + transaction_type_id + ", transaction_type_descr="
				+ transaction_type_descr + "]";
	}

	public TransactionType(long transaction_type_id, String transaction_type_descr) {
		super();
		this.transaction_type_id = transaction_type_id;
		this.transaction_type_descr = transaction_type_descr;
	}

	public TransactionType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
