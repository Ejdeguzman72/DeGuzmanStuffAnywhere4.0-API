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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.MedicalTrxDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalTrxAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.MedicalTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.MedicalTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.MedicalTrxJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.MedicalTransaction;

@Service
public class MedicalTrxService {

	@Autowired
	private MedicalTrxDaoImpl medicalTrxDaoImpl;
	
	@Autowired
	private MedicalTrxJpaDao medicalTrxDao;
	
	public MedicalTrxListResponse findAllMedicalTransactionInformation() {
		MedicalTrxListResponse response = new MedicalTrxListResponse();
		List<MedicalTrxInfoDTO> list = medicalTrxDaoImpl.findAllMedicalTransactionInformation();
		
		response.setList(list);
		return response; 
	}
	
	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(@RequestParam(required = false) String paymentDate,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<MedicalTransaction> shop = medicalTrxDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<MedicalTransaction> pageBooks = null;

			if (paymentDate == null) {
				pageBooks = medicalTrxDao.findAll(paging);
			} else {
				// pageBooks = autoShopDao.findByNameContaining(autoShopname, paging);
			}

			shop = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("transactions", shop);
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
	
	public MedicalTrxListResponse findAllMedicalTranactionsByFacility(int facility_id) {
		MedicalTrxListResponse response = new MedicalTrxListResponse();
		List<MedicalTrxInfoDTO> list = medicalTrxDaoImpl.findMedicalTransactionsByFacility(facility_id);
		
		response.setList(list);
		return response;
	}
	
	public MedicalTrxListResponse findMedicalTransactionsByType(long transaction_type_id) {
		MedicalTrxListResponse response = new MedicalTrxListResponse();
		List<MedicalTrxInfoDTO> list = medicalTrxDaoImpl.findMedicalTransactionsByType(transaction_type_id);
		
		response.setList(list);
		return response;
	}
	
	public MedicalTrxListResponse findmedicalTransactionsByUser(long user_id) {
		MedicalTrxListResponse response = new MedicalTrxListResponse();
		List<MedicalTrxInfoDTO> list = medicalTrxDaoImpl.findAllMedicalTransactionbyUser(user_id);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction> getMedicalTrxById(long medical_transaction_id) throws ResourceNotFoundException {
		return medicalTrxDaoImpl.findMedicalTransactionInformationById(medical_transaction_id);
	}
	
	public ResponseEntity<MedicalTrxInfoDTO> findMedicalTransasctionInformationDTOById(long medical_transaction_id) throws ResourceNotFoundException {
		return medicalTrxDaoImpl.findMedicalTransactionInformationDTOById(medical_transaction_id);
	}
	
	public long getCountOfMedicalTransactions() {
		return medicalTrxDaoImpl.getCountOfMedicalTransactions();
	}
	
	public MedicalTrxAddUpdateResponse addMedicalTranactionInformation(MedicalTrxAddUpdateRequest request) throws ResourceNotFoundException {
		MedicalTrxAddUpdateResponse response = new MedicalTrxAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction transaction = null;
		int count = 0;
		
		count = medicalTrxDaoImpl.addMedicalTransactionInformation(request);
		if (count > 0) {
			transaction.setAmount(request.getAmount());
			transaction.setMedical_office_id(request.getMedical_office_id());
			transaction.setMedical_transaction_date(request.getMedical_transaction_date());
			transaction.setTransaction_type_id(request.getTransaction_type_id());
			transaction.setUser_id(request.getUser_id());
			if (transaction != null) {
				response.setTransaction(transaction);
			}
		}
		
		return response;
	}
	
	public MedicalTrxAddUpdateResponse updateMedicalTransaction(MedicalTrxAddUpdateRequest request) {
		MedicalTrxAddUpdateResponse response = new MedicalTrxAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction transaction = null;
		int count = 0;
		
		count = medicalTrxDaoImpl.updateMedicalTransaction(request.getMedical_transaction_id(), request);
		if (count > 0) {
			transaction.setAmount(request.getAmount());
			transaction.setMedical_office_id(request.getMedical_office_id());
			transaction.setMedical_transaction_date(request.getMedical_transaction_date());
			transaction.setTransaction_type_id(request.getTransaction_type_id());
			transaction.setUser_id(request.getUser_id());
			if (transaction != null) {
				response.setTransaction(transaction);
			}
		}
		
		return response;
	}
	
	public int deleteMedicalTransactionInformation(long medical_transaction_id) {
		return medicalTrxDaoImpl.deleteMedicalTraansactionInformation(medical_transaction_id);
	}
	
	public DeleteAllResponse deleteAllMedicalTransactions() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = medicalTrxDaoImpl.deleteAllMedicalTransactions();
		
		response.setCount(count);
		return response;
	}
}
