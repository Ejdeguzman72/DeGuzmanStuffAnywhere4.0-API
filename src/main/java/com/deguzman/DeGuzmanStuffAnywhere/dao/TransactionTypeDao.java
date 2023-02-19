package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.model.TransactionType;

public interface TransactionTypeDao {

	public List<TransactionType> retrieveAllTransactionTypes();

	public ResponseEntity<TransactionType> retrieveTransactionTypeById(int transaction_type_id);

	public ResponseEntity<TransactionType> retrieveTransactionTypeByName(String transaction_type_descr);

}
