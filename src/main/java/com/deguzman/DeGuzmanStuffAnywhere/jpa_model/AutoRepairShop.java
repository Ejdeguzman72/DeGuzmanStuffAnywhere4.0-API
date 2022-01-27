package com.deguzman.DeGuzmanStuffAnywhere.jpa_model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;


@Table(name="auto_shop")
@Entity
@CrossOrigin
public class AutoRepairShop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7246009274943519322L;
	public int auto_shop_id;
	public String autoShopName;
	public String address;
	public String city;
	public String zip;
	public String state;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_shop_id")
	public int getAuto_shop_id() {
		return auto_shop_id;
	}
	public void setAuto_shop_id(int auto_shop_id) {
		this.auto_shop_id = auto_shop_id;
	}
	
	@Column(name = "autoShopName")
	public String getAutoShopName() {
		return autoShopName;
	}
	public void setAutoShopName(String autoShopName) {
		this.autoShopName = autoShopName;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name = "zip")
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Column(name = "state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((autoShopName == null) ? 0 : autoShopName.hashCode());
		result = prime * result + auto_shop_id;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
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
		AutoRepairShop other = (AutoRepairShop) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (autoShopName == null) {
			if (other.autoShopName != null)
				return false;
		} else if (!autoShopName.equals(other.autoShopName))
			return false;
		if (auto_shop_id != other.auto_shop_id)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
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
		return "AutoRepairShop [auto_shop_id=" + auto_shop_id + ", autoShopName=" + autoShopName + ", address="
				+ address + ", city=" + city + ", zip=" + zip + ", state=" + state + "]";
	}
	
	public AutoRepairShop(int auto_shop_id, String autoShopName, String address, String city, String zip,
			String state) {
		super();
		this.auto_shop_id = auto_shop_id;
		this.autoShopName = autoShopName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.state = state;
	}
	
	public AutoRepairShop() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
