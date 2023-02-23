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
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxDTOSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.service.GeneralTrxService;

@RestController
@RequestMapping("/app/general-transactions")
@CrossOrigin
public class GeneralTrxController {

	@Autowired
	private GeneralTrxService generalTrxService;

	@GetMapping("/all")
	@CrossOrigin
	public GeneralTrxListResponse getAllGeneralTransactionInformation() {
		GeneralTrxListResponse response = generalTrxService.findAllTransactionInformation();
		return response;
	}
	
	@GetMapping("/all-transactions")
	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(@RequestParam(required = false) String paymentDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return generalTrxService.getAllTransactionsPagination(paymentDate, page, size);
	}

	@GetMapping("/all/type/{transaction_type_id}")
	@CrossOrigin
	public GeneralTrxListResponse getTransactionsByType(@RequestBody @Valid SearchByLongRequest request) {
		GeneralTrxListResponse response = generalTrxService.findTransactionsByType(request);
		return response;
	}

	@GetMapping("/all/user/{user_id}")
	@CrossOrigin
	public GeneralTrxListResponse getTransactionsByUser(@RequestBody @Valid SearchByLongRequest request) {
		GeneralTrxListResponse response = generalTrxService.findTransactionsByUser(request);
		return response;
	}

	@GetMapping("/transaction-dto/{transaction_id}")
	@CrossOrigin
	public GeneralTrxDTOSearchResponse getTrxInformationDTOById(@RequestBody @Valid SearchByLongRequest request)
			throws ResourceNotFoundException {
		GeneralTrxDTOSearchResponse response = generalTrxService.findTranactionInformationDTOById(request);
		return response;
	}
	
	@GetMapping("/transaction/{transaction_id}")
	@CrossOrigin
	public GeneralTrxSearchResponse getTrxInformationById(@RequestBody @Valid SearchByLongRequest request)
			throws ResourceNotFoundException {
		GeneralTrxSearchResponse response = generalTrxService.findTransactionInformationById(request);
		return response;
	}

	@GetMapping("/get-transaction-count")
	@CrossOrigin
	public long getTransactionCcount() {
		return generalTrxService.findCountOfGeneralTransacion();
	}

	@PostMapping("/add-general-transaction-information")
	@CrossOrigin
	public GeneralTrxAddUpdateResponse addGeneralTrasactionInformation(@RequestBody @Valid GeneralTrxAddUpdateRequest request)
			throws ResourceNotFoundException {
		GeneralTrxAddUpdateResponse response = generalTrxService.addTransactionInformation(request);
		return response;
	}
	
	@PutMapping("/transaction/{transaction_id}")
	@CrossOrigin
	public GeneralTrxAddUpdateResponse updateTransactionInformation(@RequestBody @Valid GeneralTrxAddUpdateRequest request) throws ResourceNotFoundException {
		GeneralTrxAddUpdateResponse response = generalTrxService.updateTransactionInformation(request);
		return response;
	}

	@DeleteMapping("/transaction/{transaction_id}")
	@CrossOrigin
	public int deleteTransactionById(@RequestBody @Valid SearchByLongRequest request) {
		return generalTrxService.deleteTransactioninformation(request);
	}

	@DeleteMapping("/delete-all-transactions")
	@CrossOrigin
	public DeleteAllResponse deleteAllTransactions() {
		DeleteAllResponse response = generalTrxService.deleteAllTransactions();
		return response;
	}
}
