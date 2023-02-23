package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.deguzman.DeGuzmanStuffAnywhere.dao.UtilityDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Utility;

@Repository
public class UtilityDaoImpl implements UtilityDao {

	String GET_ALL_UTILITY_INFORMATION = "SELECT U.UTILITY_ID, U.NAME, U.PHONE, U.URL, U.DUE_DATE, UT.UTILITY_TYPE_DESCR "
			+ "FROM UTILITY U, UTILITY_TYPE UT " + "WHERE U.UTILITY_TYPE_ID = UT.UTILITY_TYPE_ID";

	String GET_UTILITY_INFORMATION_BY_DUE_DATE = "SELECT U.UTILITY_ID, U.NAME, U.PHONE, U.URL, U.DUE_DATE, UT.UTILITY_TYPE_DESCR "
			+ "FROM UTILITY U, UTILITY_TYPE UT " + "WHERE U.UTILITY_TYPE_ID = UT.UTILITY_TYPE_ID"
			+ "AND U.DUE_DATE = ?";

	String GET_UTILITY_INFORMATION_BY_ID = "SELECT U.UTILITY_ID, U.NAME, U.PHONE, U.URL, U.DUE_DATE, UT.UTILITY_TYPE_DESCR "
			+ "FROM UTILITY U, UTILITY_TYPE UT " + "WHERE U.UTILITY_TYPE_ID = UT.UTILITY_TYPE_ID"
			+ "AND U.UTILITY_ID = ?";

	String GET_UTILITY_INFORMATION_BY_NAME = "SELECT U.UTILITY_ID, U.NAME, U.PHONE, U.URL, U.DUE_DATE, UT.UTILITY_TYPE_DESCR "
			+ "FROM UTILITY U, UTILITY_TYPE UT " + "WHERE U.UTILITY_TYPE_ID = UT.UTILITY_TYPE_ID" + "AND U.NAME = ?";

	String GET_UTILITY_INFORMATION_BY_TYPE = "SELECT U.UTILITY_ID, U.NAME, U.PHONE, U.URL, U.DUE_DATE, UT.UTILITY_TYPE_DESCR "
			+ "FROM UTILITY U, UTILITY_TYPE UT " + "WHERE U.UTILITY_TYPE_ID = UT.UTILITY_TYPE_ID"
			+ "AND UT.UTILITY_TYPE_ID = ?";

	String GET_UTILITY_COUNT = "SELECT COUNT(*) FROM UTILITY";

	String ADD_UTILITY_INFORMATION = "INSERT INTO UTILITY " + "(DUE_DATE, NAME, PHONE, URL, UTILITY_TYPE_ID) "
			+ "VALUES(?, ?, ?, ?, ?);";

	String UPDATE_UTILITY_INFORMATION = "UPDATE UTILITY SET DUE_DATE = ?. NAME = ?, PHONE = ?, URL = ?, UTILITY_TYPE_ID = ?";

	String DELETE_UTILITY_INFORMATION_BY_ID = "DELETE FROM UTILITY WHERE UTILITY_ID = ?";

	String DELETE_ALL_UTILITY_INFORMATION = "DELETE FROM UTILITY";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityDaoImpl.class);

	@Override
	@Cacheable(value = "utilityList")
	public List<UtilityInfoDTO> findAllUtilityInformation() {
		List<UtilityInfoDTO> list = new ArrayList<>();
		try {
			list = jdbcTemplate.query(GET_ALL_UTILITY_INFORMATION,
					BeanPropertyRowMapper.newInstance(UtilityInfoDTO.class));

			LOGGER.info("Retrieving all utility information...");
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<UtilityInfoDTO> findUtilityInformationByDueDate(String dueDate) {
		List<UtilityInfoDTO> utilityList = new ArrayList<>();
		try {
			utilityList = jdbcTemplate.query(GET_UTILITY_INFORMATION_BY_DUE_DATE,
					(rs, rowNum) -> new UtilityInfoDTO(rs.getInt("UTILITY_ID"), rs.getString("NAME"), rs.getString("PHONE"),
							rs.getString("URL"), rs.getString("DUE_DATE"), rs.getString("UTILITY_TYPE_DESCR")),
					dueDate);

			LOGGER.info("Retrieving utilties by Due Date: " + dueDate);
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return utilityList;
	}

	@Override
	@Cacheable(value = "utilityById", key = "#utility_id")
	public UtilityInfoDTO findUtilityInformationById(long utility_id) {
		UtilityInfoDTO utilityInfo = null;
		try {
			utilityInfo = jdbcTemplate.queryForObject(GET_UTILITY_INFORMATION_BY_ID,
					BeanPropertyRowMapper.newInstance(UtilityInfoDTO.class), utility_id);

			LOGGER.info("Retrieved Utility Information with ID: " + " " + utility_id + " " + utilityInfo.getName());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return utilityInfo;
	}

	@Override
	public UtilityInfoDTO findUtilityInformationByName(String name) {
		UtilityInfoDTO utilityInfo = null;
		try {
			utilityInfo = jdbcTemplate.queryForObject(GET_UTILITY_INFORMATION_BY_NAME,
					BeanPropertyRowMapper.newInstance(UtilityInfoDTO.class), name);

			LOGGER.info("Retrieved Utility Information by name: " + utilityInfo.getName());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return utilityInfo;
	}

	@Override
	public UtilityInfoDTO findUtilityInformationByType(int utility_type_id) {
		UtilityInfoDTO utilityInfo = null;
		try {
			utilityInfo = jdbcTemplate.queryForObject(GET_UTILITY_INFORMATION_BY_TYPE,
					BeanPropertyRowMapper.newInstance(UtilityInfoDTO.class), GET_UTILITY_INFORMATION_BY_TYPE);

			LOGGER.info("Retrieved Utility Information By Utility Type ID: " + " " + utility_type_id + " "
					+ utilityInfo.getName());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return utilityInfo;
	}

	@Override
	public long findUtilityCount() {
		long count = jdbcTemplate.queryForObject(GET_UTILITY_COUNT, Integer.class);

		LOGGER.info("Getting Utility Count...");

		return count;
	}

	@Override
	@CachePut(value = "utilityList")
	public int addUtilityInformation(Utility utility) {
		int result = 0;
		try {
			int utility_type_id = utility.getUtility_type_id();
			String name = utility.getName();
			String phone = utility.getPhone();
			String url = utility.getUrl();
			String dueDate = utility.getDueDate();

			LOGGER.info("Adding Utility Information: " + name + " " + url);

			result = jdbcTemplate.update(ADD_UTILITY_INFORMATION,
					new Object[] { utility_type_id, name, phone, url, dueDate });
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	@CachePut(value = "utilityById", key = "#utility_id")
	public int updateUtilityInformation(long utility_id, Utility utilityDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@CachePut(value = "utilityById", key = "#utility_id")
	public int deleteUtilityInformation(long utility_id) {
		int count = 0;
		try {
			count = jdbcTemplate.update(DELETE_UTILITY_INFORMATION_BY_ID, utility_id);

			LOGGER.info("Deleting Utility Information by ID: " + utility_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return count;
	}

	@Override
	@CachePut(value = "utilityList")
	public int deleteAllUtilityInformation() {
		int count = 0;
		try {
			count = jdbcTemplate.update(DELETE_ALL_UTILITY_INFORMATION);

			LOGGER.info("Deleting All Utilities...");
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}

		return count;
	}

}
