package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.deguzman.DeGuzmanStuffAnywhere.dao.AutoTrxDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidTransactionTypeException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidUserException;
import com.deguzman.DeGuzmanStuffAnywhere.exception.InvalidVehicleException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction;

@Repository
public class AutoTrxDaoImpl implements AutoTrxDao {

	String GET_ALL_AUTO_TRX_INFORMATION = "SELECT AT.AUTO_TRANSACTION_ID, AT.AUTO_TRANSACTION_DATE, AT.AMOUNT, V.MAKE, V.MODEL, V.YEAR, ASH.AUTO_SHOP_NAME, US.USERNAME, TT.TRANSACTION_TYPE_DESCR "
			+ "FROM AUTO_TRANSACTIONS AT, AUTO_SHOP ASH, VEHICLE V, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE AT.AUTO_SHOP_ID = ASH.AUTO_SHOP_ID " + "	AND AT.VEHICLE_ID = V.VEHICLE_ID "
			+ "	AND AT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID " + "	AND AT.USER_ID = US.USER_ID "
			+ "ORDER BY AT.AUTO_TRANSACTION_DATE";

	String GET_ALL_AUTO_TRX_INFO_BY_TYPE = "SELECT AT.AUTO_TRANSACTION_ID, AT.AUTO_TRANSACTION_DATE, AT.AMOUNT, V.MAKE, V.MODEL, V.YEAR, ASH.AUTO_SHOP_NAME, US.USERNAME, TT.TRANSACTION_TYPE_DESCR "
			+ "FROM AUTO_TRANSACTIONS AT, AUTO_SHOP ASH, VEHICLE V, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE AT.AUTO_SHOP_ID = ASH.AUTO_SHOP_ID " + "	AND AT.VEHICLE_ID = V.VEHICLE_ID "
			+ "	AND AT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID " + "	AND AT.USER_ID = US.USER_ID"
			+ "   AND AT.TRANSACTION_TYPE_ID = ?";

	String GET_ALL_AUTO_TRX_INFO_BY_VEHICLE = "SELECT AT.AUTO_TRANSACTION_ID, AT.AUTO_TRANSACTION_DATE, AT.AMOUNT, V.MAKE, V.MODEL, V.YEAR, ASH.AUTO_SHOP_NAME, US.USERNAME, TT.TRANSACTION_TYPE_DESCR "
			+ "FROM AUTO_TRANSACTIONS AT, AUTO_SHOP ASH, VEHICLE V, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE AT.AUTO_SHOP_ID = ASH.AUTO_SHOP_ID " + "	AND AT.VEHICLE_ID = V.VEHICLE_ID "
			+ "	AND AT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID " + "	AND AT.USER_ID = US.USER_ID"
			+ "   AND AT.VEHICLE_ID = ?";

	String GET_ALL_AUTO_TRX_BY_USER = "SELECT AT.AUTO_TRANSACTION_ID, AT.AUTO_TRANSACTION_DATE, AT.AMOUNT, V.MAKE, V.MODEL, V.YEAR, ASH.AUTO_SHOP_NAME, US.USERNAME, TT.TRANSACTION_TYPE_DESCR "
			+ "FROM AUTO_TRANSACTIONS AT, AUTO_SHOP ASH, VEHICLE V, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE AT.AUTO_SHOP_ID = ASH.AUTO_SHOP_ID " + "	AND AT.VEHICLE_ID = V.VEHICLE_ID "
			+ "	AND AT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID " + "	AND AT.USER_ID = US.USER_ID"
			+ "   AND AT.USER_ID = ?";

	String GET_AUTO_TRX_DTO_INFO_BY_ID = "SELECT AT.AUTO_TRANSACTION_ID, AT.AUTO_TRANSACTION_DATE, AT.AMOUNT, V.MAKE, V.MODEL, V.YEAR, ASH.AUTO_SHOP_NAME, US.USERNAME, TT.TRANSACTION_TYPE_DESCR "
			+ "FROM AUTO_TRANSACTIONS AT, AUTO_SHOP ASH, VEHICLE V, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE AT.AUTO_SHOP_ID = ASH.AUTO_SHOP_ID " + "	AND AT.VEHICLE_ID = V.VEHICLE_ID "
			+ "	AND AT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID " + "	AND AT.USER_ID = US.USER_ID"
			+ "   AND AT.AUTO_TRANSACTION_ID = ?";

	String GET_AUTO_TRANSACTION_INFO = "SELECT * FROM AUTO_TRANSACTIONS WHERE AUTO_TRANSACTION_ID = ?";
	
	String GET_AUTO_TRX_COUNT = "SELECT COUNT(*) FROM AUTO_TRANSACTIONS";

	String ADD_AUTO_TRX_INFO = "INSERT INTO AUTO_TRANSACTIONS "
			+ "(AMOUNT, AUTO_TRANSACTION_DATE, AUTO_SHOP_ID, VEHICLE_ID, TRANSACTION_TYPE_ID, USER_ID) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
	
	String UPDATE_AUTO_TRANSACTION_INFORMATION = "UPDATE AUTO_TRANSACTIONS "
			+ "SET AMOUNT = ?, "
			+ "AUTO_TRANSACTION_DATE = ?, "
			+ "AUTO_SHOP_ID = ?, "
			+ "VEHICLE_ID = ?, "
			+ "TRANSACTION_TYPE_ID = ?, "
			+ "USER_ID = ? "
			+ "WHERE AUTO_TRANSACTION_ID = ?";

	String DELETE_AUTO_TRX_BY_ID = "DELETE FROM AUTO_TRANSACTIONS WHERE AUTO_TRANSACTION_ID = ?";

	String DELETE_ALL_AUTO_TRX = "DELETE FROM AUTO_TRANSACTIONS";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoTrxDaoImpl.class);

	@Override
	@Cacheable(value = "autoTransactionList")
	public List<AutoTrxInfoDTO> findAllAutoTransactionInformation() {
		List<AutoTrxInfoDTO> autoTrxList = new ArrayList<>();
		try {
			autoTrxList = jdbcTemplate.query(GET_ALL_AUTO_TRX_INFORMATION,
					BeanPropertyRowMapper.newInstance(AutoTrxInfoDTO.class));
			
			LOGGER.info("Retrieving all Auto Transactions...");			
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return autoTrxList;
	}

	@Override
	public List<AutoTrxInfoDTO> findAutoTransactionsByVehicle(long vehicle_id) {
		List<AutoTrxInfoDTO> autoTrxListVehicle = new ArrayList<>();
		try {
			autoTrxListVehicle = jdbcTemplate.query(GET_ALL_AUTO_TRX_INFO_BY_VEHICLE,
					(rs, rowNum) -> new AutoTrxInfoDTO(rs.getLong("AUTO_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("AUTO_TRANSACTION_DATE"), rs.getString("MAKE"), rs.getString("MODEL"),
							rs.getString("YEAR"), rs.getString("AUTO_SHOP_NAME"), rs.getString("USERNAME"),
							rs.getString("TRANSACTION_TYPE_DESCR")),
					vehicle_id);

			LOGGER.info("Retriving all Auto Transactions by vehicle_id: " + vehicle_id);

		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return autoTrxListVehicle;
	}

	@Override
	public List<AutoTrxInfoDTO> findAutoTransactionsByUser(long user_id) {
		List<AutoTrxInfoDTO> autoTrxListUser = new ArrayList<>();
		try {
			autoTrxListUser = jdbcTemplate.query(GET_ALL_AUTO_TRX_BY_USER,
					(rs, rowNum) -> new AutoTrxInfoDTO(rs.getLong("AUTO_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("AUTO_TRANSACTION_DATE"), rs.getString("MAKE"), rs.getString("MODEL"),
							rs.getString("YEAR"), rs.getString("AUTO_SHOP_NAME"), rs.getString("USERNAME"),
							rs.getString("TRANSACTION_TYPE_DESCR")),
					user_id);

			LOGGER.info("Retrieving all Auto Transactions by user_id: " + user_id);
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}

		return autoTrxListUser;
	}

	@Override
	public List<AutoTrxInfoDTO> findAutoTransactionsByType(long transaction_type_id) {
		List<AutoTrxInfoDTO> autoTrxListType = new ArrayList<>();
		try {
			autoTrxListType = jdbcTemplate.query(GET_ALL_AUTO_TRX_INFO_BY_TYPE,
					(rs, rowNum) -> new AutoTrxInfoDTO(rs.getLong("AUTO_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("AUTO_TRANSACTION_DATE"), rs.getString("MAKE"), rs.getString("MODEL"),
							rs.getString("YEAR"), rs.getString("AUTO_SHOP_NAME"), rs.getString("USERNAME"),
							rs.getString("TRANSACTION_TYPE_DESCR")),
					transaction_type_id);

			LOGGER.info("Retrieving all Auto Transactions by transaction_type_id: " + transaction_type_id);
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		

		return autoTrxListType;
	}

	@Override
	@Cacheable(value = "autoTrasactionById", key = "#auto_transaction_id")
	public ResponseEntity<AutoTrxInfoDTO> findAutoTranasctionInformatioDTOnById(long auto_transaction_id)
			throws InvalidTransactionException {
		AutoTrxInfoDTO autoTrxInfo = new AutoTrxInfoDTO();
		try {
			autoTrxInfo = jdbcTemplate.queryForObject(GET_AUTO_TRX_DTO_INFO_BY_ID,
					BeanPropertyRowMapper.newInstance(AutoTrxInfoDTO.class), auto_transaction_id);

			LOGGER.info("Retrieving transaction based on ID: " + " " + auto_transaction_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		

		return ResponseEntity.ok().body(autoTrxInfo);
	}
	
	@Override
	public ResponseEntity<AutoTransaction> findAutoTranasctionInformationById(long auto_transaction_id) throws InvalidTransactionException {
		AutoTransaction transaction = jdbcTemplate.queryForObject(GET_AUTO_TRANSACTION_INFO,
				BeanPropertyRowMapper.newInstance(AutoTransaction.class), auto_transaction_id);
		
		LOGGER.info("Retrieving transaction based on ID: " + " " + auto_transaction_id);
		
		return ResponseEntity.ok().body(transaction);
	}

	@Override
	public long getCountOfAutoTransactions() {
		long count = jdbcTemplate.queryForObject(GET_AUTO_TRX_COUNT, Integer.class);

		LOGGER.info("Retrieving count of Auto Transactions...");

		return count;
	}

	@Override
	@CachePut(value = "autoTransactionList")
	public int addAutoTransactionInformation(AutoTransaction autoTransaction) throws InvalidAutoShopException,
			InvalidUserException, InvalidTransactionTypeException, InvalidVehicleException {
		int count = 0;
		
		try {
			double amount = autoTransaction.getAmount();
			String auto_transaction_date = autoTransaction.getAuto_transaction_date();
			int autoShop = autoTransaction.getAuto_shop_id();
			long transactionType = autoTransaction.getTransaction_type_id();
			long vehicle = autoTransaction.getVehicle_id();
			long user = autoTransaction.getUser_id();
			
			count = jdbcTemplate.update(ADD_AUTO_TRX_INFO,
					new Object[] { amount, auto_transaction_date, autoShop, transactionType, vehicle, user });

			LOGGER.info("Adding Auto Transaction: " + " " + amount);
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return count;
	}

	@Override
	@CachePut(value = "autoTrasactionById", key = "#auto_transcation_id")
	public int updateTransactionInformation(long auto_transaction_id, AutoTransaction autoTransactionDetails)
			throws InvalidAutoShopException, InvalidVehicleException, InvalidTransactionTypeException,
			InvalidUserException {

		int result = 0;
		AutoTransaction transaction = new AutoTransaction();
		try {
			transaction = jdbcTemplate.queryForObject(GET_AUTO_TRANSACTION_INFO,
					BeanPropertyRowMapper.newInstance(AutoTransaction.class), auto_transaction_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		try {
			if (transaction != null) {
				transaction.setAmount(autoTransactionDetails.getAmount());
				transaction.setAuto_transaction_date(autoTransactionDetails.getAuto_transaction_date());
				transaction.setAuto_shop_id(autoTransactionDetails.getAuto_shop_id());
				transaction.setTransaction_type_id(autoTransactionDetails.getTransaction_type_id());
				transaction.setVehicle_id(autoTransactionDetails.getVehicle_id());
				transaction.setUser_id(autoTransactionDetails.getUser_id());
				transaction.setAuto_transaction_id(auto_transaction_id);
				
				result = jdbcTemplate.update(UPDATE_AUTO_TRANSACTION_INFORMATION, new Object[] {
						transaction.getAmount(),
						transaction.getAuto_transaction_date(),
						transaction.getAuto_shop_id(),
						transaction.getTransaction_type_id(),
						transaction.getVehicle_id(),
						transaction.getUser_id(),
						transaction.getAuto_transaction_id()
				});
				
				LOGGER.info("Updating Auto Tranasction Information with auto_transaction_id: " + auto_transaction_id);
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "autoTrasactionById", key = "#auto_tranasction_id")
	public int deleteAutoTransactionInformation(long auto_transaction_id) {
		int count = 0;
		try {
			count = jdbcTemplate.update(DELETE_AUTO_TRX_BY_ID, auto_transaction_id);
			
			LOGGER.info("Deleting Auto Transaction with ID: " + auto_transaction_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error: " + e.toString());
		}

		return count;
	}

	@Override
	@CachePut(value = "autoTransactionList")
	public int deleteAllAutoTransactions() {
		int count = jdbcTemplate.update(DELETE_ALL_AUTO_TRX);

		LOGGER.info("Deleting All Auto Transactions");

		return count;
	}

}
