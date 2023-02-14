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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.MedicalOfficeDaoImpl;
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
	public int addMedicalOfficeInformation(@RequestBody MedicalOffice medicalOffice) throws DuplicateOfficeException {
		return medOfficeService.addMedicalOfficeInformation(medicalOffice);
	}
	
	@PutMapping("/medical-office/{medicalOfficeId}")
	@CrossOrigin
	public int updateMedicalOfficeInformation(@PathVariable long medicalOfficeId, @RequestBody MedicalOffice officeDetails) {
		return medOfficeService.updateMedicalOfficeInformation(medicalOfficeId, officeDetails);
	}

	@DeleteMapping("/medical-office/{medicalOfficeId}")
	@CrossOrigin
	public int deleteMedicalOfficeById(@PathVariable long medicalOfficeId) {
		return medOfficeService.deleteMedicalOfficeById(medicalOfficeId);
	}

	@DeleteMapping("/delete-all-medical-offices")
	@CrossOrigin
	public int deleteAllMedicalOffices() {
		return medOfficeService.deleteAllMedicalOfficeInformation();
	}
}
