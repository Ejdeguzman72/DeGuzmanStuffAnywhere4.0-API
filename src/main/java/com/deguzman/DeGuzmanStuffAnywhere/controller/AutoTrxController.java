package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxDTOSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UriConstants;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionTypeException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidUserException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.service.AutoTrxService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AutoTrxController {
	
	@Autowired
	private AutoTrxService autoTrxService;

	@GetMapping(value = UriConstants.GET_ALL_AUTO_TRX)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxListResponse getAllAutoTransactionInformation() {
		AutoTrxListResponse response = autoTrxService.findAllAutoTransactionInformation();
		return response;
	}
	
	@GetMapping(value = UriConstants.GET_ALL_AUTO_TRX_PAGINATION)
	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(@RequestParam(required = false) String paymentDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return autoTrxService.getAllTransactionsPagination(paymentDate, page, size);
	}

	@GetMapping(value = UriConstants.GET_AUTO_TRX_BY_VEHICLE)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxListResponse getAutoTransactionsByVehicle(@RequestBody @Valid SearchByLongRequest request) {
		AutoTrxListResponse response = autoTrxService.findAutoTransactionsByVehicle(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_TRX_BY_USER)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxListResponse getAutoTransactionsByUser(@RequestBody @Valid SearchByLongRequest request) {
		AutoTrxListResponse response = autoTrxService.findAutoTransactionsByUser(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_TRX_BY_TYPE)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxListResponse getAutoTransactionsByType(@RequestBody @Valid SearchByLongRequest request) {
		AutoTrxListResponse response = autoTrxService.findAutoTransactionsByType(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_TRX_DTO_BY_ID)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxDTOSearchResponse getAutoTransactionDTOById(@RequestBody @Valid SearchByLongRequest request)
			throws InvalidTransactionException {
		AutoTrxDTOSearchResponse response = autoTrxService.findAutoTransactionInformationDTOById(request);
		return response;
	}
	
	@GetMapping(value = UriConstants.GET_AUTO_TRX_BY_ID)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxSearchResponse getAutoTransactionById(@RequestBody @Valid SearchByLongRequest request)
			throws InvalidTransactionException {
		AutoTrxSearchResponse response = autoTrxService.findAutoTransactionInformationById(request);
		return response;
	}

	@GetMapping(value = UriConstants.GET_AUTO_TRX_COUNT)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public long getAutoTransactionCount() {
		return autoTrxService.getCountOfAutoTransactions();
	}

	@PostMapping(value = UriConstants.ADD_AUTO_TRX)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxAddUpdateResponse addAutoTransactionInformation(@RequestBody @Valid AutoTrxAddUpdateRequest request)
			throws InvalidAutoShopException, InvalidUserException, InvalidTransactionTypeException,
			InvalidVehicleException {
		AutoTrxAddUpdateResponse response = autoTrxService.addAutoTranactionInformation(request);
		return response;
	}
	
	@PutMapping(value = UriConstants.UPDATE_AUTO_TRX)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxAddUpdateResponse updateAutoTransactionInformation(@RequestBody @Valid AutoTrxAddUpdateRequest request) throws InvalidAutoShopException, InvalidVehicleException, InvalidTransactionTypeException, InvalidUserException {
		AutoTrxAddUpdateResponse response = autoTrxService.updateTransactionInformation(request);
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_AUTO_TRX)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxSearchResponse deleteAutoTransactionById(@RequestBody @Valid SearchByLongRequest request) throws InvalidTransactionException {
		AutoTrxSearchResponse response = autoTrxService.deleteAutoTransactionInformation(request);
		return response;
	}

	@DeleteMapping(value = UriConstants.DELETE_ALL_AUTO_TRX)
	@CrossOrigin(origins = "*", maxAge = 3600)
	public DeleteAllResponse deleteAllAutoTransactions() {
		DeleteAllResponse response = autoTrxService.deleteAllAutoTransactions();
		return response;
	}
}
