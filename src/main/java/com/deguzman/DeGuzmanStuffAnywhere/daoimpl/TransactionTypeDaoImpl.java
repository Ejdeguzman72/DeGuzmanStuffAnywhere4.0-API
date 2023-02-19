package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.deguzman.DeGuzmanStuffAnywhere.dao.TransactionTypeDao;
import com.deguzman.DeGuzmanStuffAnywhere.model.TransactionType;

@Repository
public class TransactionTypeDaoImpl implements TransactionTypeDao {

	String GET_ALL_TRANSACTION_TYPES = "SELECT * FROM TRANSACTION_TYPE";
	String GET_TRANSACTION_TYPE_BY_ID = "SELECT * FROM TRANSACTION_TYPE WHERE TRANSACTION_TYPE_ID = ?";
	String GET_TRANSACTION_TYPE_BY_NAME = "SELECT * FROM TRANSACTION_TYPE WHERE TRANSACTION_TYPE_DESCR = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTypeDaoImpl.class);

	@Override
	public List<TransactionType> retrieveAllTransactionTypes() {
		List<TransactionType> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_TRANSACTION_TYPES,
					BeanPropertyRowMapper.newInstance(TransactionType.class));

			LOGGER.info("Retrieving all transaction types...");
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ResponseEntity<TransactionType> retrieveTransactionTypeById(int transaction_type_id) {
		TransactionType trxType = null;
		try {
			trxType = jdbcTemplate.queryForObject(GET_TRANSACTION_TYPE_BY_ID,
					BeanPropertyRowMapper.newInstance(TransactionType.class), transaction_type_id);

			LOGGER.info("Retrieved Transaction Type: " + " " + transaction_type_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
			e.printStackTrace();
		}

		return ResponseEntity.ok().body(trxType);
	}

	@Override
	public ResponseEntity<TransactionType> retrieveTransactionTypeByName(String transaction_type_descr) {
		TransactionType trxType = null;
		try {
			trxType = jdbcTemplate.queryForObject(GET_TRANSACTION_TYPE_BY_NAME,
					BeanPropertyRowMapper.newInstance(TransactionType.class), transaction_type_descr);

			LOGGER.info("Retrived Transaction Type: " + " " + transaction_type_descr);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
			e.printStackTrace();
		}

		return ResponseEntity.ok().body(trxType);
	}

}
