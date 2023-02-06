package com.deguzman.DeGuzmanStuffAnywhere.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@CrossOrigin
public class MedicalTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 938725339019416975L;
	public long medical_transaction_id;
	
	@NotNull(message = "Invalid request, medical_transaction_date field is null/missing")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public String medical_transaction_date;
	
	@NotNull(message = "Invald request, amount is null/missing")
	@Pattern(regexp = "^(?:[01]\\d|5[0123]).(?:[012345]\\d)", message = "Bad request, amount field must follow the proper format. $00000.00")
	public double amount;
	
	@NotNull(message = "Invalid request, medical_office_id field is missing/null")
	@Pattern(regexp = "[0-9]{0,50}", message = "Invalid request, medical_office_id field can only contain letters, special characters are not allowed")
	public int medical_office_id;
	
	@NotNull(message = "Invalid request, transaction_type_id field is missing/null")
	@Pattern(regexp = "[0-9]{0,50}", message = "Invalid request, transaction_type_id field can only contain letters, special characters are not allowed")
	public long transaction_type_id;
	
	@NotNull(message = "Invalid request, user_id field is missing/null")
	@Pattern(regexp = "[0-9]{0,50}", message = "Invalid request, user_id field can only contain letters, special characters are not allowed")
	public long user_id;

	public long getMedical_transaction_id() {
		return medical_transaction_id;
	}

	public void setMedical_transaction_id(long medical_transaction_id) {
		this.medical_transaction_id = medical_transaction_id;
	}

	public String getMedical_transaction_date() {
		return medical_transaction_date;
	}

	public void setMedical_transaction_date(String medical_transaction_date) {
		this.medical_transaction_date = medical_transaction_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getMedical_office_id() {
		return medical_office_id;
	}

	public void setMedical_office_id(int medical_office_id) {
		this.medical_office_id = medical_office_id;
	}

	public long getTransaction_type_id() {
		return transaction_type_id;
	}

	public void setTransaction_type_id(long transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + medical_office_id;
		result = prime * result + ((medical_transaction_date == null) ? 0 : medical_transaction_date.hashCode());
		result = prime * result + (int) (medical_transaction_id ^ (medical_transaction_id >>> 32));
		result = prime * result + (int) (transaction_type_id ^ (transaction_type_id >>> 32));
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
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
		MedicalTransaction other = (MedicalTransaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (medical_office_id != other.medical_office_id)
			return false;
		if (medical_transaction_date == null) {
			if (other.medical_transaction_date != null)
				return false;
		} else if (!medical_transaction_date.equals(other.medical_transaction_date))
			return false;
		if (medical_transaction_id != other.medical_transaction_id)
			return false;
		if (transaction_type_id != other.transaction_type_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedicalTransaction [medical_transaction_id=" + medical_transaction_id + ", medical_transaction_date="
				+ medical_transaction_date + ", amount=" + amount + ", medical_office_id=" + medical_office_id
				+ ", transaction_type_id=" + transaction_type_id + ", user_id=" + user_id + "]";
	}

	public MedicalTransaction(long medical_transaction_id, String medical_transaction_date, double amount,
			int medical_office_id, long transaction_type_id, long user_id) {
		super();
		this.medical_transaction_id = medical_transaction_id;
		this.medical_transaction_date = medical_transaction_date;
		this.amount = amount;
		this.medical_office_id = medical_office_id;
		this.transaction_type_id = transaction_type_id;
		this.user_id = user_id;
	}

	public MedicalTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
