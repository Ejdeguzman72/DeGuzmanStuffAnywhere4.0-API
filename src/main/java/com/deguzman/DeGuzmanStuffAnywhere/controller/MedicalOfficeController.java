package com.deguzman.DeGuzmanStuffAnywhere.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.MedicalOfficeDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/medical-offices")
@CrossOrigin
public class MedicalOfficeController {

	@Autowired
	private MedicalOfficeDaoImpl medicalOfficeDaoImpl;
	
	@GetMapping("/all")
	public List<MedicalOffice> getAllMedicalOfficeInformation() {
		return medicalOfficeDaoImpl.findAllMedicalOfficeInformation();
	}
	
	@GetMapping("/medical-office/zip/{zip}")
	public List<MedicalOffice> getAllMedicalOfficeInformationByZipCode(@PathVariable String zip) {
		return medicalOfficeDaoImpl.findMedicalOfficesByZip(zip);
	}
	
	@GetMapping("/medical-office/{medicalOfficeId}")
	public ResponseEntity<MedicalOffice> getMedicalOfficeInformationById(@PathVariable long medicalOfficeId) {
		return medicalOfficeDaoImpl.findMedicalOfficeInformationById(medicalOfficeId);
	}
	
	@GetMapping("/medical-office-count")
	public long getMedicalOfficeCount() {
		return medicalOfficeDaoImpl.getMedicalOfficeCount();
	}
	
	@PostMapping("/add-medical-office-information")
	public int addMedicalOfficeInformation(@RequestBody MedicalOffice medicalOffice) {
		return medicalOfficeDaoImpl.addMedicalOfficeInformation(medicalOffice);
	}
	
	@DeleteMapping("/medical-office/{medicalOfficeId}")
	public int deleteMedicalOfficeById(@PathVariable long medicalOfficeId) {
		return medicalOfficeDaoImpl.deleteMedicalOfficeById(medicalOfficeId);
	}
	
	@DeleteMapping("/delete-all-medical-offices")
	public int deleteAllMedicalOffices() {
		return medicalOfficeDaoImpl.deleteAllMedicalOfficeInformation();
	}
}