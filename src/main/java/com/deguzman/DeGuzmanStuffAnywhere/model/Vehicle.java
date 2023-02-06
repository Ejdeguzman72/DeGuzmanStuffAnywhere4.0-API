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
public class Vehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6819954124384869848L;
	public long vehicleId;
	
	@NotNull(message = "Invalid request, year field is missing/null")
	@Pattern(regexp = "[0-9]{4}", message = "Invalid request, year field can only contain letters, special characters are not allowed")
	public String year;
	
	@NotNull(message = "Invalid request, make field is missing/null")
	@Pattern(regexp = "[a-zA-Z,0-9]{0,50}", message = "Invalid request, make field can only contain letters, special characters are not allowed")
	public String make;
	
	@NotNull(message = "Invalid request, model model is missing/null")
	@Pattern(regexp = "[a-zA-Z,0-9]{0,50}", message = "Invalid request, model field can only contain letters, special characters are not allowed")
	public String model;
	
	@NotNull(message = "Invalid request, capacity field is missing/null")
	@Pattern(regexp = "[0-9]{1,2}", message = "Invalid request, capacity field can only contain letters, special characters are not allowed")
	public int capacity;
	
	@NotNull(message = "Invalid request, transmission field is missing/null")
	@Pattern(regexp = "[a-zA-Z]{0,50}", message = "Invalid request, transmission field can only contain letters, special characters are not allowed")
	public String transmission;

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		result = prime * result + (int) (vehicleId ^ (vehicleId >>> 32));
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (capacity != other.capacity)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		if (vehicleId != other.vehicleId)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", year=" + year + ", make=" + make + ", model=" + model
				+ ", capacity=" + capacity + ", transmission=" + transmission + "]";
	}

	public Vehicle(long vehicleId, String year, String make, String model, int capacity, String transmission) {
		super();
		this.vehicleId = vehicleId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.capacity = capacity;
		this.transmission = transmission;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

}
