package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;
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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.AutoTrxDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoShopListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionTypeException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidUserException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction;
import com.deguzman.DeGuzmanStuffAnywhere.service.AutoTrxService;

@RestController
@RequestMapping("/app/auto-transactions")
@CrossOrigin
public class AutoTrxController {
	
	@Autowired
	private AutoTrxService autoTrxService;

	@GetMapping("/all")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public AutoTrxListResponse getAllAutoTransactionInformation() {
		AutoTrxListResponse response = autoTrxService.findAllAutoTransactionInformation();
		return response;
	}
	
	@GetMapping("/all-transactions")
	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(@RequestParam(required = false) String paymentDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return autoTrxService.getAllTransactionsPagination(paymentDate, page, size);
	}

	@GetMapping("/all/vehicle/{vehicle_id}")
	@CrossOrigin
	public AutoTrxListResponse getAutoTransactionsByVehicle(@PathVariable long vehicle_id) {
		AutoTrxListResponse response = autoTrxService.findAutoTransactionsByVehicle(vehicle_id);
		return response;
	}

	@GetMapping("/all/users/{user_id}")
	@CrossOrigin
	public AutoTrxListResponse getAutoTransactionsByUser(@PathVariable long user_id) {
		AutoTrxListResponse response = autoTrxService.findAutoTransactionsByUser(user_id);
		return response;
	}

	@GetMapping("/all/type/{transaction_type_id}")
	@CrossOrigin
	public AutoTrxListResponse getAutoTransactionsByType(@PathVariable long transaction_type_id) {
		AutoTrxListResponse response = autoTrxService.findAutoTransactionsByType(transaction_type_id);
		return response;
	}

	@GetMapping("/auto-transaction-dto/{auto_transaction_id}")
	@CrossOrigin
	public ResponseEntity<AutoTrxInfoDTO> getAutoTransactionDTOById(@PathVariable long auto_transaction_id)
			throws InvalidTransactionException {
		return autoTrxService.findAutoTransactionInformationDTOById(auto_transaction_id);
	}
	
	@GetMapping("/auto-transaction/{auto_transaction_id}")
	@CrossOrigin
	public ResponseEntity<AutoTransaction> getAutoTransactionById(@PathVariable long auto_transaction_id)
			throws InvalidTransactionException {
		return autoTrxService.findAutoTransactionInformationById(auto_transaction_id);
	}

	@GetMapping("/auto-transaction-count")
	@CrossOrigin
	public long getAutoTransactionCount() {
		return autoTrxService.getCountOfAutoTransactions();
	}

	@PostMapping("/add-auto-transaction-information")
	@CrossOrigin
	public int addAutoTransactionInformation(@RequestBody @Valid AutoTrxAddUpdateRequest request)
			throws InvalidAutoShopException, InvalidUserException, InvalidTransactionTypeException,
			InvalidVehicleException {
		return autoTrxService.addAutoTranactionInformation(request);
	}
	
	@PutMapping("/auto-transaction/{auto_transaction_id}")
	@CrossOrigin
	public int updateAutoTransactionInformation(@PathVariable long auto_transaction_id, @RequestBody AutoTransaction autoTransactionDetails) throws InvalidAutoShopException, InvalidVehicleException, InvalidTransactionTypeException, InvalidUserException {
		return autoTrxService.updateTransactionInformation(auto_transaction_id, autoTransactionDetails);
	}

	@DeleteMapping("/auto-transaction/{auto_transaction_id}")
	@CrossOrigin
	public int deleteAutoTransactionById(@PathVariable long auto_transaction_id) {
		return autoTrxService.deleteAutoTransactionInformation(auto_transaction_id);
	}

	@DeleteMapping("/delete-all-transactions")
	@CrossOrigin
	public int deleteAllAutoTransactions() {
		return autoTrxService.deleteAllAutoTransactions();
	}
}
