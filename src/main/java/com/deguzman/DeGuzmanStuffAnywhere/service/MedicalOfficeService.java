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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.MedicalOfficeDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalOfficeAddRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalOfficeListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateOfficeException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.MedicalOfficeJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.MedicalOffice;

@Service
public class MedicalOfficeService {

	@Autowired
	private MedicalOfficeDaoImpl medicalOfficeDaoImpl;
	
	@Autowired
	private MedicalOfficeJpaDao medOfficeJpaDao;
	
	public MedicalOfficeListResponse findAllmedicalOfficeInformation() {
		MedicalOfficeListResponse response = new MedicalOfficeListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice> list = medicalOfficeDaoImpl.findAllMedicalOfficeInformation();
		
		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllMedicalOfficesPagination(
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			List<MedicalOffice> shop = medOfficeJpaDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<MedicalOffice> pageBooks = null;

			if (name == null) {
				pageBooks = medOfficeJpaDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("medicalOffices", shop);
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
	
	public MedicalOfficeListResponse findMedicalofficesByZip(String zip) {
		MedicalOfficeListResponse response = new MedicalOfficeListResponse();
		List<com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice> list = medicalOfficeDaoImpl.findMedicalOfficesByZip(zip);
		
		response.setList(list);
		return response;
	}
	
	public long getMedicalOfficeCount() {
		return medicalOfficeDaoImpl.getMedicalOfficeCount();
	}
	
	public int addMedicalOfficeInformation(MedicalOfficeAddRequest request) throws DuplicateOfficeException {
		return medicalOfficeDaoImpl.addMedicalOfficeInformation(request);
	}
	
	public int updateMedicalOfficeInformation(long medicalOfficeId, com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice officeDetails) {
		return medicalOfficeDaoImpl.updateMedicalOfficeInformation(medicalOfficeId, officeDetails);
	}
	
	public int deleteMedicalOfficeById(long medicalOfficeId) {
		return medicalOfficeDaoImpl.deleteMedicalOfficeById(medicalOfficeId);
	}
	
	public int deleteAllMedicalOfficeInformation() {
		return medicalOfficeDaoImpl.deleteAllMedicalOfficeInformation();
	}

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice> findMedicalOfficeInformationById(
			long medicalOfficeId) {
		return medicalOfficeDaoImpl.findMedicalOfficeInformationById(medicalOfficeId);
	}
}
