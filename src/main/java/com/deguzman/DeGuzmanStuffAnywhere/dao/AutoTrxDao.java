package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionTypeException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidUserException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction;

public interface AutoTrxDao {

	public List<AutoTrxInfoDTO> findAllAutoTransactionInformation();

	public List<AutoTrxInfoDTO> findAutoTransactionsByVehicle(long vehicle_id);

	public List<AutoTrxInfoDTO> findAutoTransactionsByUser(long user_id);

	public List<AutoTrxInfoDTO> findAutoTransactionsByType(long transaction_type_id);

	public AutoTransaction findAutoTranasctionInformationById(long auto_transaction_id)
			throws InvalidTransactionException;

	public long getCountOfAutoTransactions();

	public int addAutoTransactionInformation(AutoTransaction autoTransaction)
			throws InvalidAutoShopException, InvalidUserException, InvalidTransactionTypeException,
			InvalidVehicleException;

	public int updateTransactionInformation(long auto_transaction_id,
			AutoTransaction autoTransactionDetails) throws InvalidAutoShopException,
			InvalidVehicleException, InvalidTransactionTypeException, InvalidUserException;

	public int deleteAutoTransactionInformation(long auto_transaction_id);

	public int deleteAllAutoTransactions();

	AutoTrxInfoDTO findAutoTranasctionInformatioDTOnById(long auto_transaction_id)
			throws InvalidTransactionException;

}
