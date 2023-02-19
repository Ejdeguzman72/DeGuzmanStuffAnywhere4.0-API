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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.GeneralTrxDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.GeneralTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.GeneralTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.GeneralTrxJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.GeneralTransaction;

@Service
public class GeneralTrxService {

	@Autowired
	private GeneralTrxDaoImpl generalTrxDaoImpl;

	@Autowired
	private GeneralTrxJpaDao generalTrxDao;

	public GeneralTrxListResponse findAllTransactionInformation() {
		GeneralTrxListResponse response = new GeneralTrxListResponse();
		List<GeneralTrxInfoDTO> list = generalTrxDaoImpl.findAllTransactionInformation();

		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(
			@RequestParam(required = false) String paymentDate, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			List<GeneralTransaction> shop = generalTrxDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<GeneralTransaction> pageBooks = null;

			if (paymentDate == null) {
				pageBooks = generalTrxDao.findAll(paging);
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

	public GeneralTrxListResponse findTransactionsByUser(long user_id) {
		GeneralTrxListResponse response = new GeneralTrxListResponse();
		List<GeneralTrxInfoDTO> list = generalTrxDaoImpl.findTransactionsByUser(user_id);

		response.setList(list);
		return response;
	}

	public GeneralTrxListResponse findTransactionsByType(long transaction_type_id) {
		GeneralTrxListResponse response = new GeneralTrxListResponse();
		List<GeneralTrxInfoDTO> list = generalTrxDaoImpl.findTransactionsByType(transaction_type_id);

		response.setList(list);
		return response;
	}

	public ResponseEntity<GeneralTrxInfoDTO> findTranactionInformationDTOById(long transaction_id)
			throws ResourceNotFoundException {
		return generalTrxDaoImpl.findTransactionInformationDTOById(transaction_id);
	}

	public long findCountOfGeneralTransacion() {
		return generalTrxDaoImpl.findCountOfGeneralTransaction();
	}

	public GeneralTrxAddUpdateResponse addTransactionInformation(GeneralTrxAddUpdateRequest request)
			throws ResourceNotFoundException {
		GeneralTrxAddUpdateResponse response = new GeneralTrxAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.GeneralTransaction transaction = null;
		int count = 0;

		count = generalTrxDaoImpl.addTransactionInformation(request);
		if (count > 0) {
			transaction.setAmount(request.getAmount());
			transaction.setEntity(request.getEntity());
			transaction.setPayment_date(request.getPayment_date());
			transaction.setTransaction_type_id(request.getTransaction_type_id());
			transaction.setUser_id(request.getUser_id());

			if (transaction != null) {
				response.setTransaction(transaction);
			}
		}

		return response;
	}

	public GeneralTrxAddUpdateResponse updateTransactionInformation(GeneralTrxAddUpdateRequest request) {
		GeneralTrxAddUpdateResponse response = new GeneralTrxAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.GeneralTransaction transaction = null;
		int count = 0;

		count = generalTrxDaoImpl.updateTransactionInformation(request.getTransaction_id(), request);
		if (count > 0) {
			transaction.setAmount(request.getAmount());
			transaction.setEntity(request.getEntity());
			transaction.setPayment_date(request.getPayment_date());
			transaction.setTransaction_type_id(request.getTransaction_type_id());
			transaction.setUser_id(request.getUser_id());
			if (transaction != null) {
				response.setTransaction(transaction);
			}
		}

		return response;
	}

	public int deleteTransactioninformation(long transaction_id) {
		return generalTrxDaoImpl.deleteTransactionInformation(transaction_id);
	}

	public DeleteAllResponse deleteAllTransactions() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = generalTrxDaoImpl.deleteAllTransactions();

		response.setCount(count);

		return response;
	}

	public ResponseEntity<com.deguzman.DeGuzmanStuffAnywhere.model.GeneralTransaction> findTransactionInformationById(
			long transaction_id) throws ResourceNotFoundException {
		return generalTrxDaoImpl.findTransactionInformationById(transaction_id);
	}
}
