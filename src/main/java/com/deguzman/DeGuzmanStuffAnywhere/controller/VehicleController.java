package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.Map;

import javax.validation.Valid;

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

import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByDescr;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByIntRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByNameRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehicleListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehicleSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehilceAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehilceAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
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
	public VehicleSearchResponse getVehicleInformationById(@RequestBody @Valid SearchByLongRequest request)
			throws InvalidVehicleException {
		VehicleSearchResponse response = vehicleService.findVehicleInformationById(request);
		return response;
	}

	@GetMapping("/vehicle/make/{make}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByMake(@RequestBody @Valid SearchByNameRequest request) {
		VehicleListResponse response = vehicleService.findVehicleInformationByMake(request);
		return response;
	}

	@GetMapping("/vehicle/model/{model}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByModel(@RequestBody @Valid SearchByNameRequest request) {
		VehicleListResponse response = vehicleService.findVehicleInformationbyModel(request);
		return response;
	}

	@GetMapping("/vehicle/year/{year}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByYear(@RequestBody @Valid SearchByDescr request) {
		VehicleListResponse response = vehicleService.findVehicleInformationByYear(request);
		return response;
	}

	@GetMapping("/vehicle/transmission/{transmission}")
	@CrossOrigin
	public VehicleListResponse getVehicleInformationByTransmission(@RequestBody @Valid SearchByNameRequest request) {
		VehicleListResponse response = vehicleService.findVehicleInformationByTransmission(request);
		return response;
	}

	@GetMapping("/vehicle-count")
	@CrossOrigin
	public int getVehicleCount() {
		return vehicleService.getCountOfVehicles();
	}

	@PostMapping("/add-vehicle-information")
	@CrossOrigin
	public VehilceAddUpdateResponse addVehicleInformation(@RequestBody @Valid VehilceAddUpdateRequest request) {
		VehilceAddUpdateResponse response = vehicleService.addVehicleInformation(request);
		return response;
	}
	
	@PutMapping("/vehicle/{vehicleId}")
	@CrossOrigin
	public VehilceAddUpdateResponse updateVehicleInformation(@RequestBody @Valid VehilceAddUpdateRequest request) throws InvalidVehicleException {
		VehilceAddUpdateResponse response = vehicleService.updateVehicleInfomration(request);
		return response;
	}

	@DeleteMapping("/vehicle/{vehicleId}")
	@CrossOrigin
	public VehicleSearchResponse deleteVehicleInformationbyId(@RequestBody @Valid SearchByLongRequest request) throws InvalidVehicleException {
		VehicleSearchResponse response = vehicleService.deleteVehicleInformation(request);
		return response;
	}

	@DeleteMapping("/delete-all-vehicles")
	@CrossOrigin
	public DeleteAllResponse deleteAllVehicleInformation() {
		DeleteAllResponse response = vehicleService.deleteAllVehicleInformation();
		return response;
	}

}
