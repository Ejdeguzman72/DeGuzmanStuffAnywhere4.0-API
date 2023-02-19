package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle;

public interface VehicleDao {

	public List<Vehicle> findAllCarInformation();

	public ResponseEntity<Vehicle> findVehicleInformationById(long vehicleId)
			throws InvalidVehicleException;

	public List<Vehicle> findVehicleInformatioByMake(String make);

	public List<Vehicle> findVehicleInformationByModel(String model);

	public List<Vehicle> findVehicleInformationByYear(String year);

	public List<Vehicle> findVehicleInformationByTransmission(String transmission);

	public int getCountofCars();

	public int addCarInformation(Vehicle vehicle);

	public int updateCarInformation(long vehicleId, Vehicle vehicleDetails);

	public int deleteCarInformation(long vehicleId);

	public int deleteAllVehicleInformation();
}
