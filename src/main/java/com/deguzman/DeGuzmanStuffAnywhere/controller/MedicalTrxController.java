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
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalTrxAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.MedicalTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction;
import com.deguzman.DeGuzmanStuffAnywhere.service.MedicalTrxService;

@RestController
@RequestMapping("/app/medical-transactions")
@CrossOrigin
public class MedicalTrxController {

	@Autowired
	private MedicalTrxService medicalService;
	
	@Autowired
	private MedicalTrxService medicalTrxPageService;

	@GetMapping("/all")
	@CrossOrigin
	public MedicalTrxListResponse getAllMedicalTrxInformation() {
		return medicalService.findAllMedicalTransactionInformation();
	}
	
	@GetMapping("/all-transactions")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(@RequestParam(required = false) String paymentDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		return medicalTrxPageService.getAllTransactionsPagination(paymentDate, page, size);
	}


	@GetMapping("/all/facility/{facility_id}")
	@CrossOrigin
	public MedicalTrxListResponse getAllMedicalTrxInformationByFacility(@PathVariable int facility_id) {
		return medicalService.findAllMedicalTranactionsByFacility(facility_id);
	}

	@GetMapping("/all/type/{transaction_type_id}")
	@CrossOrigin
	public MedicalTrxListResponse getAllMedicalTrxInformationByType(@PathVariable long transaction_type_id) {
		return medicalService.findMedicalTransactionsByType(transaction_type_id);
	}

	@GetMapping("/all/user/{user_id}")
	@CrossOrigin
	public MedicalTrxListResponse getAllMedicalTrxInformationByUser(@PathVariable long user_id) {
		return medicalService.findmedicalTransactionsByUser(user_id);
	}

	@GetMapping("/medical-transaction/{medical_transaction_id}")
	@CrossOrigin
	public ResponseEntity<MedicalTransaction> getMedicalTrxById(@PathVariable long medical_transaction_id)
			throws ResourceNotFoundException {
		return medicalService.getMedicalTrxById(medical_transaction_id);
	}
	
	@GetMapping("/medical-transaction-dto/{medical_transaction_id}")
	@CrossOrigin
	public ResponseEntity<MedicalTrxInfoDTO> getMedicalTrxDTOById(@PathVariable long medical_transaction_id)
			throws ResourceNotFoundException {
		return medicalService.findMedicalTransasctionInformationDTOById(medical_transaction_id);
	}

	@GetMapping("/get-medical-trx-count")
	@CrossOrigin
	public long getMedicalTrxCount() {
		return medicalService.getCountOfMedicalTransactions();
	}

	@PostMapping("/add-medical-transaction")
	@CrossOrigin
	public MedicalTrxAddUpdateResponse addMedicalTransaction(@RequestBody @Valid MedicalTrxAddUpdateRequest request) throws ResourceNotFoundException {
		MedicalTrxAddUpdateResponse response =medicalService.addMedicalTranactionInformation(request);
		return response;
	}
	
	@PutMapping("/medical-transaction/{medical_transaction_id}")
	@CrossOrigin
	public MedicalTrxAddUpdateResponse updateMedicalTransactionInformation(@RequestBody @Valid MedicalTrxAddUpdateRequest request) {
		MedicalTrxAddUpdateResponse response = medicalService.updateMedicalTransaction(request);
		return response;
	}

	@DeleteMapping("/medical-transaction/{medical_transaction_id}")
	@CrossOrigin
	public int deleteMedicalTrxById(@PathVariable long medical_transaction_id) {
		return medicalService.deleteMedicalTransactionInformation(medical_transaction_id);
	}

	@DeleteMapping("/delete-all-medical-transactions")
	@CrossOrigin
	public DeleteAllResponse deleteAllMedicalTransactions() {
		DeleteAllResponse response = medicalService.deleteAllMedicalTransactions();
		return response;
	}
}
