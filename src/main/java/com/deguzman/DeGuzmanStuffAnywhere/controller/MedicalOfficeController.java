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

import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalOfficeAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalOfficeAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalOfficeListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateOfficeException;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice;
import com.deguzman.DeGuzmanStuffAnywhere.service.MedicalOfficeService;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/medical-offices")
@CrossOrigin
public class MedicalOfficeController {

	@Autowired
	private MedicalOfficeService medOfficeService;

	@GetMapping("/all")
	@CrossOrigin
	public MedicalOfficeListResponse getAllMedicalOfficeInformation() {
		MedicalOfficeListResponse response = medOfficeService.findAllmedicalOfficeInformation();
		return response;
	}

	@GetMapping("all-medical-offices")
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> getAllMedicalOfficesPagination(
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return medOfficeService.getAllMedicalOfficesPagination(name, page, size);
	}

	@GetMapping("/medical-office/zip/{zip}")
	@CrossOrigin
	public MedicalOfficeListResponse getAllMedicalOfficeInformationByZipCode(@PathVariable String zip) {
		MedicalOfficeListResponse response = medOfficeService.findMedicalofficesByZip(zip);
		return response;
	}

	@GetMapping("/medical-office/{medicalOfficeId}")
	@CrossOrigin
	public ResponseEntity<MedicalOffice> getMedicalOfficeInformationById(@PathVariable long medicalOfficeId) {
		return medOfficeService.findMedicalOfficeInformationById(medicalOfficeId);
	}

	@GetMapping("/medical-office-count")
	@CrossOrigin
	public long getMedicalOfficeCount() {
		return medOfficeService.getMedicalOfficeCount();
	}

	@PostMapping("/add-medical-office-information")
	@CrossOrigin
	public MedicalOfficeAddUpdateResponse addMedicalOfficeInformation(@RequestBody @Valid MedicalOfficeAddUpdateRequest request) throws DuplicateOfficeException {
		MedicalOfficeAddUpdateResponse response = medOfficeService.addMedicalOfficeInformation(request);
		return response;
	}
	
	@PutMapping("/medical-office/{medicalOfficeId}")
	@CrossOrigin
	public MedicalOfficeAddUpdateResponse updateMedicalOfficeInformation(@RequestBody @Valid MedicalOfficeAddUpdateRequest request) {
		MedicalOfficeAddUpdateResponse response = medOfficeService.updateMedicalOfficeInformation(request);
		return response;
	}

	@DeleteMapping("/medical-office/{medicalOfficeId}")
	@CrossOrigin
	public int deleteMedicalOfficeById(@PathVariable long medicalOfficeId) {
		return medOfficeService.deleteMedicalOfficeById(medicalOfficeId);
	}

	@DeleteMapping("/delete-all-medical-offices")
	@CrossOrigin
	public DeleteAllResponse deleteAllMedicalOffices() {
		DeleteAllResponse response = medOfficeService.deleteAllMedicalOfficeInformation();
		return response;
	}
}
