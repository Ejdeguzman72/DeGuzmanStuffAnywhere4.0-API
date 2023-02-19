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

import com.deguzman.DeGuzmanStuffAnywhere.dao.MedicalTrxDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.MedicalTrxInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.exception.ResourceNotFoundException;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction;

@Repository
public class MedicalTrxDaoImpl implements MedicalTrxDao {

	String GET_ALL_MEDICAL_TRANSACTION_INFO = "SELECT MT.MEDICAL_TRANSACTION_ID,MT.AMOUNT,MT.MEDICAL_TRANSACTION_DATE, MO.NAME AS FACILITYNAME,MO.ADDRESS,MO.CITY,MO.STATE,MO.ZIP,TT.TRANSACTION_TYPE_DESCR, US.USERNAME AS NAMEOFUSER "
			+ "FROM MEDICAL_TRANSACTIONS MT, MEDICAL_OFFICE MO, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE MT.MEDICAL_OFFICE_ID = MO.MEDICAL_OFFICE_ID AND MT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID  AND MT.USER_ID = US.USER_ID "
			+ "ORDER BY MT.MEDICAL_TRANSACTION_DATE DESC";

	String GET_ALL_MEDICAL_TRANSACTIONS_BY_FACILITY = "SELECT MT.MEDICAL_TRANSACTION_ID,MT.AMOUNT,MT.MEDICAL_TRANSACTION_DATE, MO.NAME AS FACILITYNAME,MO.ADDRESS,MO.CITY,MO.STATE,MO.ZIP,TT.TRANSACTION_TYPE_DESCR, US.USERNAME AS NAMEOFUSER "
			+ "FROM MEDICAL_TRANSACTIONS MT, MEDICAL_OFFICE MO, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE MT.MEDICAL_OFFICE_ID = MO.MEDICAL_OFFICE_ID AND MT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID  AND MT.USER_ID = US.USER_ID "
			+ "AND MT.MEDICAL_OFFICE_ID = ? " + "ORDER BY MT.MEDICAL_TRANSACTION_DATE DESC";

	String GET_ALL_MEDICAL_TRANSACTIONS_BY_TYPE = "SELECT MT.MEDICAL_TRANSACTION_ID,MT.AMOUNT,MT.MEDICAL_TRANSACTION_DATE, MO.NAME AS FACILITYNAME,MO.ADDRESS,MO.CITY,MO.STATE,MO.ZIP,TT.TRANSACTION_TYPE_DESCR, US.USERNAME AS NAMEOFUSER "
			+ "FROM MEDICAL_TRANSACTIONS MT, MEDICAL_OFFICE MO, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE MT.MEDICAL_OFFICE_ID = MO.MEDICAL_OFFICE_ID AND MT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID  AND MT.USER_ID = US.USER_ID "
			+ "AND MT.TRANSACTION_TYPE_ID = ? " + "ORDER BY MT.MEDICAL_TRANSACTION_DATE DESC";

	String GET_ALL_MEDICAL_TRANSACTIONS_BY_USER = "SELECT MT.MEDICAL_TRANSACTION_ID,MT.AMOUNT,MT.MEDICAL_TRANSACTION_DATE, MO.NAME AS FACILITYNAME,MO.ADDRESS,MO.CITY,MO.STATE,MO.ZIP,TT.TRANSACTION_TYPE_DESCR, US.USERNAME AS NAMEOFUSER "
			+ "FROM MEDICAL_TRANSACTIONS MT, MEDICAL_OFFICE MO, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE MT.MEDICAL_OFFICE_ID = MO.MEDICAL_OFFICE_ID AND MT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID  AND MT.USER_ID = US.USER_ID "
			+ "AND MT.USER_ID = ? " + "ORDER BY MT.MEDICAL_TRANSACTION_DATE DESC";

	String GET_MEDICAL_TRANSACTION_BY_ID = "SELECT MT.MEDICAL_TRANSACTION_ID,MT.AMOUNT,MT.MEDICAL_TRANSACTION_DATE, MO.NAME AS FACILITYNAME,MO.ADDRESS,MO.CITY,MO.STATE,MO.ZIP,TT.TRANSACTION_TYPE_DESCR, US.USERNAME AS NAMEOFUSER "
			+ "FROM MEDICAL_TRANSACTIONS MT, MEDICAL_OFFICE MO, TRANSACTION_TYPE TT, USERS US "
			+ "WHERE MT.MEDICAL_OFFICE_ID = MO.MEDICAL_OFFICE_ID AND MT.TRANSACTION_TYPE_ID = TT.TRANSACTION_TYPE_ID  AND MT.USER_ID = US.USER_ID "
			+ "AND MT.MEDICAL_TRANSACTION_ID = ?";
	
	String GET_MEDICAL_TRANSACTION_INFO = "SELECT * FROM MEDICAL_TRANSACTIONS WHERE MEDICAL_TRANSACTION_ID = ?";

	String GET_MEDICAL_TRX_COUNT = "SELECT COUNT(*) FROM MEDICAL_TRANSACTIONS";

	String ADD_MEDICAL_TRANSACTION = "INSERT INTO MEDICAL_TRANSACTIONS "
			+ "(AMOUNT, MEDICAL_TRANSACTION_DATE, MEDICAL_OFFICE_ID, TRANSACTION_TYPE_ID, USER_ID) "
			+ "VALUES(?, ?, ?, ?, ?)";

	String UPDATE_MEDICAL_TRANSACTION = "UPDATE MEDICAL_TRANSACTIONS "
			+ "SET AMOUNT = ?, "
			+ "MEDICAL_TRANSACTION_DATE = ?, "
			+ "MEDICAL_OFFICE_ID = ?, "
			+ "TRANSACTION_TYPE_ID = ?, "
			+ "USER_ID = ? "
			+ "WHERE MEDICAL_TRANSACTION_ID = ?";

	String DELETE_MEDICAL_TRANSACTION_BY_ID = "DELETE FROM MEDICAL_TRANSACTIONS WHERE MEDICAL_TRANSACTION_ID = ?";

	String DELETE_ALL_MEDICAL_TRANSACTIONS = "DELETE FROM MEDICAL_TRANSACTIONS";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(MedicalTrxDaoImpl.class);

	@Override
	@Cacheable(value = "medicalTrasactionList")
	public List<MedicalTrxInfoDTO> findAllMedicalTransactionInformation() {
		List<MedicalTrxInfoDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_MEDICAL_TRANSACTION_INFO,
					(rs, rowNum) -> new MedicalTrxInfoDTO(rs.getLong("MEDICAL_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("MEDICAL_TRANSACTION_DATE"), rs.getString("FACILITYNAME"), rs.getString("ADDRESS"),
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
							rs.getString("TRANSACTION_TYPE_DESCR"), rs.getString("NAMEOFUSER")));
			
			LOGGER.info("Retrieving all medical transactions...");			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return list;
	}

	@Override
	public List<MedicalTrxInfoDTO> findMedicalTransactionsByFacility(int facility_id) {
		List<MedicalTrxInfoDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_MEDICAL_TRANSACTIONS_BY_FACILITY,
					(rs, rowNum) -> new MedicalTrxInfoDTO(rs.getLong("MEDICAL_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("MEDICAL_TRANSACTION_DATE"), rs.getString("FACILITYNAME"), rs.getString("ADDRESS"),
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
							rs.getString("TRANSACTION_TYPE_DESCR"), rs.getString("NAMEOFUSER")),
					facility_id);
			
			LOGGER.info("Retrieving All Medical Transactions by facility_id: " + facility_id);			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return list;

	}

	@Override
	public List<MedicalTrxInfoDTO> findMedicalTransactionsByType(long transaction_type_id) {
		List<MedicalTrxInfoDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_MEDICAL_TRANSACTIONS_BY_TYPE,
					(rs, rowNum) -> new MedicalTrxInfoDTO(rs.getLong("MEDICAL_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("MEDICAL_TRANSACTION_DATE"), rs.getString("FACILITYNAME"), rs.getString("ADDRESS"),
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
							rs.getString("TRANSACTION_TYPE_DESCR"), rs.getString("NAMEOFUSER")),
					transaction_type_id);
			
			LOGGER.info("Retrieving All Medical Transactions by transaction_type_id: " + transaction_type_id);			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return list;
	}

	@Override
	public List<MedicalTrxInfoDTO> findAllMedicalTransactionbyUser(long user_id) {
		List<MedicalTrxInfoDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_MEDICAL_TRANSACTIONS_BY_USER,
					(rs, rowNum) -> new MedicalTrxInfoDTO(rs.getLong("MEDICAL_TRANSACTION_ID"), rs.getDouble("AMOUNT"),
							rs.getString("MEDICAL_TRANSACTION_DATE"), rs.getString("FACILITYNAME"), rs.getString("ADDRESS"),
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP"),
							rs.getString("TRANSACTION_TYPE_DESCR"), rs.getString("NAMEOFUSER")),
					user_id);
			
			LOGGER.info("Retrieving All Medical Transactions by user_id: " + user_id);			
		} catch (Exception e) { 
			LOGGER.error("Exception: " + e.toString());
		}

		return list;
	}

	@Override
	@Cacheable(value = "medicalTransactionById", key = "#medical_transaction_id")
	public ResponseEntity<MedicalTrxInfoDTO> findMedicalTransactionInformationDTOById(
			long medical_transaction_id) throws ResourceNotFoundException {
		MedicalTrxInfoDTO medicalTrx = new MedicalTrxInfoDTO();
		
		try {
			medicalTrx = jdbcTemplate.queryForObject(GET_MEDICAL_TRANSACTION_BY_ID,
					BeanPropertyRowMapper.newInstance(MedicalTrxInfoDTO.class), medical_transaction_id);
			
			LOGGER.info("Retrieving Medical Transaction by ID: " + medical_transaction_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return ResponseEntity.ok().body(medicalTrx);
	}

	@Override
	public ResponseEntity<MedicalTransaction> findMedicalTransactionInformationById(
			long medical_transaction_id) throws ResourceNotFoundException {
		MedicalTransaction medicalTrx = new MedicalTransaction();
		
		try {
			medicalTrx = jdbcTemplate.queryForObject(GET_MEDICAL_TRANSACTION_INFO,
					BeanPropertyRowMapper.newInstance(MedicalTransaction.class), medical_transaction_id);
			
			LOGGER.info("Retrieving Medical Transaction by ID: " + medical_transaction_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return ResponseEntity.ok().body(medicalTrx);
	}
	
	@Override
	public long getCountOfMedicalTransactions() {
		long count = jdbcTemplate.queryForObject(GET_MEDICAL_TRX_COUNT, Integer.class);

		LOGGER.info("Getting the medical transaction count...");

		return count;
	}

	@Override
	@CachePut(value = "medicalTrasactionList")
	public int addMedicalTransactionInformation(MedicalTransaction medicalTransaction)
			throws ResourceNotFoundException {

		int result = 0;
		
		try {
			double amount = medicalTransaction.getAmount();
			String medicalTrxDate = medicalTransaction.getMedical_transaction_date();
			int medicalOffice = medicalTransaction.getMedical_office_id();
			long transactionType = medicalTransaction.getTransaction_type_id();
			long user = medicalTransaction.getUser_id();
			
			LOGGER.info("Adding medical transaction..." + " " + amount + " " + medicalTrxDate);
			
			result = jdbcTemplate.update(ADD_MEDICAL_TRANSACTION,
					new Object[] { amount, medicalTrxDate, medicalOffice, transactionType, user });			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "medicalTransactionById", key = "#medical_transaction_id")
	public int updateMedicalTransaction(long medical_transaction_id, MedicalTransaction medicalTransactionDetails) {

		int result = 0;
		MedicalTransaction transaction = new MedicalTransaction();
		
		try {
			transaction = jdbcTemplate.queryForObject(GET_MEDICAL_TRANSACTION_INFO,
					BeanPropertyRowMapper.newInstance(MedicalTransaction.class), medical_transaction_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		
		try {
			if (transaction != null) {
				transaction.setAmount(medicalTransactionDetails.getAmount());
				transaction.setMedical_transaction_date(medicalTransactionDetails.getMedical_transaction_date());
				transaction.setMedical_office_id(medicalTransactionDetails.getMedical_office_id());
				transaction.setTransaction_type_id(medicalTransactionDetails.getTransaction_type_id());
				transaction.setUser_id(medicalTransactionDetails.getUser_id());
				transaction.setMedical_transaction_id(medical_transaction_id);
				
				result = jdbcTemplate.update(UPDATE_MEDICAL_TRANSACTION, new Object[] {
						transaction.getAmount(),
						transaction.getMedical_transaction_date(),
						transaction.getMedical_office_id(),
						transaction.getTransaction_type_id(),
						transaction.getUser_id(),
						transaction.getMedical_transaction_id()
				});
				
				LOGGER.info("Updating medical transaction info for medical_transaction_id: " + medical_transaction_id);
			
			}
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "medicalTransactionById", key = "#medical_transaction_id")
	public int deleteMedicalTraansactionInformation(long medical_transaction_id) {
		int result = 0;
		
		try {
			result = jdbcTemplate.update(DELETE_MEDICAL_TRANSACTION_BY_ID, medical_transaction_id);

			LOGGER.info("Deleting Medical Transaction based on medical_transaction_id: " + medical_transaction_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "medicalTrasactionList")
	public int deleteAllMedicalTransactions() {
		int result = 0;
		
		try {
			result = jdbcTemplate.update(DELETE_ALL_MEDICAL_TRANSACTIONS);

			LOGGER.info("Deleting All Medical Transactions...");	
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

}
