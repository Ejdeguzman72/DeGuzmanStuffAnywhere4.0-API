package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.VehicleDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehicleListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle;
import com.deguzman.DeGuzmanStuffAnywhere.service.VehicleService;

@RestController
@RequestMapping("/app/vehicles")
@CrossOrigin
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/all")
	@CrossOrigin
	public VehicleListResponse getAllVehicleInformation() {
		VehicleListResponse response = vehicleService.findAllVehicleInformation();
		return response;
	}

	@GetMapping("/all-vehicles")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllVehiclesPagination(@RequestParam(required = false) String model,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return vehicleService.getAllVehiclesPagination(model, page, size);
	}

	@GetMapping("/vehicle/{vehicleId}")
	@CrossOrigin
	public ResponseEntity<Vehicle> getVehicleInformationById(@PathVariable int vehicleId)
			throws InvalidVehicleException {
		return vehicleService.findVehicleInformationById(vehicleId);
	}

	@GetMapping("/vehicle/make/{make}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByMake(@PathVariable String make) {
		VehicleListResponse response = vehicleService.findVehicleInformationByMake(make);
		return response;
	}

	@GetMapping("/vehicle/model/{model}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByModel(@PathVariable String model) {
		VehicleListResponse response = vehicleService.findVehicleInformationbyModel(model);
		return response;
	}

	@GetMapping("/vehicle/year/{year}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByYear(@PathVariable String year) {
		VehicleListResponse response = vehicleService.findVehicleInformationByYear(year);
		return response;
	}

	@GetMapping("/vehicle/transmission/{transmission}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByTransmission(@PathVariable String transmission) {
		VehicleListResponse response = vehicleService.findVehicleInformationByTransmission(transmission);
		return response;
	}

	@GetMapping("/vehicle-count")
	@CrossOrigin
	public int getVehicleCount() {
		return vehicleService.getCountOfVehicles();
	}

	@PostMapping("/add-vehicle-information")
	@CrossOrigin
	public int addVehicleInformation(@RequestBody Vehicle vehicle) {
		return vehicleService.addVehicleInformation(vehicle);
	}
	
	@PutMapping("/vehicle/{vehicleId}")
	@CrossOrigin
	public int updateVehicleInformation(@PathVariable int vehicleId, @RequestBody Vehicle vehicleDetails) {
		return vehicleService.updateVehicleInfomration(vehicleId, vehicleDetails);
	}

	@DeleteMapping("/vehicle/{vehicleId}")
	@CrossOrigin
	public int deleteVehicleInformationbyId(@PathVariable int vehicleId) {
		return vehicleService.deleteVehicleInformation(vehicleId);
	}

	@DeleteMapping("/delete-all-vehicles")
	@CrossOrigin
	public int deleteAllVehicleInformation() {
		return vehicleService.deleteAllVehicleInformation();
	}

}
