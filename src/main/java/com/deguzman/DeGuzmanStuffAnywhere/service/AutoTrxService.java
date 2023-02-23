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

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.AutoTrxDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxAddUpdateRequest;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxAddUpdateResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.AutoTrxDTOSearchResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.SearchByLongRequest;
import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionTypeException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidUserException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_dao.AutoTrxJpaDao;
import com.deguzman.DeGuzmanStuffAnywhere.jpa_model.AutoTransaction;

@Service
public class AutoTrxService {

	@Autowired
	private AutoTrxDaoImpl autoTrxDaoImpl;

	@Autowired
	private AutoTrxJpaDao autoTrxDao;

	public AutoTrxListResponse findAllAutoTransactionInformation() {
		AutoTrxListResponse response = new AutoTrxListResponse();
		List<AutoTrxInfoDTO> list = autoTrxDaoImpl.findAllAutoTransactionInformation();

		response.setList(list);
		return response;
	}

	public ResponseEntity<Map<String, Object>> getAllTransactionsPagination(
			@RequestParam(required = false) String paymentDate, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		try {

			List<AutoTransaction> shop = autoTrxDao.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<AutoTransaction> pageBooks = null;

			if (paymentDate == null) {
				pageBooks = autoTrxDao.findAll(paging);
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

	public AutoTrxListResponse findAutoTransactionsByVehicle(SearchByLongRequest request) {
		AutoTrxListResponse response = new AutoTrxListResponse();
		List<AutoTrxInfoDTO> list = autoTrxDaoImpl.findAutoTransactionsByVehicle(request.getId());

		response.setList(list);
		return response;
	}

	public AutoTrxListResponse findAutoTransactionsByUser(SearchByLongRequest request) {
		AutoTrxListResponse response = new AutoTrxListResponse();
		List<AutoTrxInfoDTO> list = autoTrxDaoImpl.findAutoTransactionsByUser(request.getId());

		response.setList(list);
		return response;
	}

	public AutoTrxListResponse findAutoTransactionsByType(SearchByLongRequest request) {
		AutoTrxListResponse response = new AutoTrxListResponse();
		List<AutoTrxInfoDTO> list = autoTrxDaoImpl.findAutoTransactionsByType(request.getId());

		response.setList(list);
		return response;
	}

	public AutoTrxDTOSearchResponse findAutoTransactionInformationDTOById(SearchByLongRequest request)
			throws InvalidTransactionException {
		AutoTrxDTOSearchResponse response = new AutoTrxDTOSearchResponse();
		AutoTrxInfoDTO transaction = autoTrxDaoImpl.findAutoTranasctionInformatioDTOnById(request.getId());
		
		response.setTransaction(transaction);
		return response;
	}

	public AutoTrxSearchResponse findAutoTransactionInformationById(SearchByLongRequest request) throws InvalidTransactionException {
		AutoTrxSearchResponse response = new AutoTrxSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction transaction = autoTrxDaoImpl.findAutoTranasctionInformationById(request.getId());
		response.setTransaction(transaction);
		
		return response;
	}

	public long getCountOfAutoTransactions() {
		return autoTrxDaoImpl.getCountOfAutoTransactions();
	}

	public AutoTrxAddUpdateResponse addAutoTranactionInformation(AutoTrxAddUpdateRequest request)
			throws InvalidAutoShopException, InvalidUserException, InvalidTransactionTypeException,
			InvalidVehicleException {
		AutoTrxAddUpdateResponse response = new AutoTrxAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction transaction = null;
		int count = 0;

		count = autoTrxDaoImpl.addAutoTransactionInformation(request);
		if (count > 0) {
			transaction.setAmount(request.getAmount());
			transaction.setAuto_shop_id(request.getAuto_shop_id());
			transaction.setAuto_transaction_date(request.getAuto_transaction_date());
			transaction.setTransaction_type_id(request.getTransaction_type_id());
			transaction.setUser_id(request.getUser_id());
			transaction.setVehicle_id(request.getVehicle_id());
			if (transaction != null) {
				response.setTransaction(transaction);
			}
		}

		return response;
	}

	public AutoTrxAddUpdateResponse updateTransactionInformation(AutoTrxAddUpdateRequest request)
			throws InvalidAutoShopException, InvalidVehicleException, InvalidTransactionTypeException,
			InvalidUserException {
		AutoTrxAddUpdateResponse response = new AutoTrxAddUpdateResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction transaction = null;
		int count = 0;

		count = autoTrxDaoImpl.updateTransactionInformation(request.getAuto_transaction_id(), request);
		if (count > 0) {
			transaction.setAmount(request.getAmount());
			transaction.setAuto_shop_id(request.getAuto_shop_id());
			transaction.setAuto_transaction_date(request.getAuto_transaction_date());
			transaction.setTransaction_type_id(request.getTransaction_type_id());
			transaction.setUser_id(request.getUser_id());
			transaction.setVehicle_id(request.getVehicle_id());
			if (transaction != null) {
				response.setTransaction(transaction);
			}
		}

		return response;
	}

	public AutoTrxSearchResponse deleteAutoTransactionInformation(SearchByLongRequest request) throws InvalidTransactionException {
		AutoTrxSearchResponse response  = new AutoTrxSearchResponse();
		com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction transaction = autoTrxDaoImpl.findAutoTranasctionInformationById(request.getId());
		int count = 0;
		count = autoTrxDaoImpl.deleteAutoTransactionInformation(request.getId());
		
		if (count > 0) {
			response.setTransaction(transaction);
		}
		
		return response;
	}

	public DeleteAllResponse deleteAllAutoTransactions() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = autoTrxDaoImpl.deleteAllAutoTransactions();

		response.setCount(count);

		return response;
	}
}
