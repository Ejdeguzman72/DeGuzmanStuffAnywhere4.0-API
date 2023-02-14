package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dto.GeneralTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.GeneralTransaction;

public interface GeneralTrxDao {

	public List<GeneralTrxInfoDTO> findAllTransactionInformation();

	public List<GeneralTrxInfoDTO> findTransactionsByUser(long user_id);

	public List<GeneralTrxInfoDTO> findTransactionsByType(long transaction_type_id);

	public ResponseEntity<GeneralTrxInfoDTO> findTransactionInformationDTOById(long transaction_id)
			throws ResourceNotFoundException;
	
	public ResponseEntity<GeneralTransaction> findTransactionInformationById(long transaction_id)
			throws ResourceNotFoundException;

	public long findCountOfGeneralTransaction();

	public int addTransactionInformation(GeneralTransaction transaction) throws ResourceNotFoundException;

	public int updateTransactionInformation(Long transaction_id,
			GeneralTransaction transactionDetails);

	public int deleteTransactionInformation(long transaction_id);

	public int deleteAllTransactions();

}
