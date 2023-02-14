package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dto.MedicalTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction;

public interface MedicalTrxDao {

	public List<MedicalTrxInfoDTO> findAllMedicalTransactionInformation();

	public List<MedicalTrxInfoDTO> findMedicalTransactionsByFacility(int facility_id);

	public List<MedicalTrxInfoDTO> findMedicalTransactionsByType(long transaction_type_id);

	public List<MedicalTrxInfoDTO> findAllMedicalTransactionbyUser(long user_id);

	public ResponseEntity<MedicalTrxInfoDTO> findMedicalTransactionInformationDTOById(
			long medical_transaction_id) throws ResourceNotFoundException;
	
	public ResponseEntity<MedicalTransaction> findMedicalTransactionInformationById(
			long medical_transaction_id) throws ResourceNotFoundException;

	public long getCountOfMedicalTransactions();

	public int addMedicalTransactionInformation(MedicalTransaction medicalTransaction)
			throws ResourceNotFoundException;

	public int updateMedicalTransaction(long medicalTransactionId,
			MedicalTransaction medicalTransactionDetails);

	public int deleteMedicalTraansactionInformation(long medical_transaction_id);

	public int deleteAllMedicalTransactions();

}
