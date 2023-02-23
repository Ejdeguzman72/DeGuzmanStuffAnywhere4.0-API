package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.VehicleDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehicleListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehicleSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehilceAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.VehilceAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.VehicleJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.Vehicle;

@Service
public class VehicleService {

	@Autowired
	private VehicleDaoImpl vehicleDaoImpl;

	@Autowired
	private VehicleJpaDao vehicleJpaDao;

	public VehicleListResponse findAllVehicleInformation() {
		VehicleListResponse response = new VehicleListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle> list = vehicleDaoImpl.findAllCarInformation();

		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllVehiclesPagination(@RequestParam(required = false) String model,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<Vehicle> shop = vehicleJpaDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Vehicle> pageBooks = null;

			if (model == null) {
				pageBooks = vehicleJpaDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("vehicles", shop);
			response.put("currentPage", pageBooks.getNumber());
			response.put("totalItems", pageBooks.getTotalElements());
			response.put("totalPages", pageBooks.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public VehicleSearchResponse findVehicleInformationById(long vehicleId)
			throws InvalidVehicleException {
		VehicleSearchResponse response = new VehicleSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle vehicle = vehicleDaoImpl.findVehicleInformationById(vehicleId);
		
		response.setVehicle(vehicle);
		return response;
	}

	public VehicleListResponse findVehicleInformationByMake(String make) {
		VehicleListResponse response = new VehicleListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle> list = vehicleDaoImpl.findVehicleInformatioByMake(make);

		response.setList(list);
		return response;
	}

	public VehicleListResponse findVehicleInformationbyModel(String model) {
		VehicleListResponse response = new VehicleListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle> list = vehicleDaoImpl
				.findVehicleInformationByModel(model);

		response.setList(list);
		return response;
	}

	public VehicleListResponse findVehicleInformationByYear(String year) {
		VehicleListResponse response = new VehicleListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle> list = vehicleDaoImpl.findVehicleInformationByYear(year);

		response.setList(list);
		return response;
	}

	public VehicleListResponse findVehicleInformationByTransmission(String transmission) {
		VehicleListResponse response = new VehicleListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle> list = vehicleDaoImpl
				.findVehicleInformationByTransmission(transmission);

		response.setList(list);
		return response;
	}

	public VehilceAddUpdateResponse addVehicleInformation(VehilceAddUpdateRequest request) {
		VehilceAddUpdateResponse response = new VehilceAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle vehicle = null;
		int count = 0;

		try {
			count = vehicleDaoImpl.addCarInformation(request);
			if (count > 0) {
				vehicle.setCapacity(request.getCapacity());
				vehicle.setMake(request.getMake());
				vehicle.setModel(request.getModel());
				vehicle.setTransmission(request.getTransmission());
				vehicle.setYear(request.getYear());
				if (vehicle != null) {
					response.setVehicle(vehicle);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	public VehilceAddUpdateResponse updateVehicleInfomration(VehilceAddUpdateRequest request) {
		VehilceAddUpdateResponse response = new VehilceAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.Vehicle vehicle = null;
		int count = 0;
		
		count = vehicleDaoImpl.updateCarInformation(request.getVehicleId(), request);
		if (count > 0) {
			vehicle.setCapacity(request.getCapacity());
			vehicle.setMake(request.getMake());
			vehicle.setModel(request.getModel());
			vehicle.setTransmission(request.getTransmission());
			vehicle.setYear(request.getYear());
			if (vehicle != null) {
				response.setVehicle(vehicle);
			}
		}

		return response;
	}

	public int deleteVehicleInformation(long vehicleId) {
		return vehicleDaoImpl.deleteCarInformation(vehicleId);
	}

	public DeleteAllResponse deleteAllVehicleInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = vehicleDaoImpl.deleteAllVehicleInformation();
		
		response.setCount(count);
		return response;
	}

	public int getCountOfVehicles() {
		return vehicleDaoImpl.getCountofCars();
	}
}
