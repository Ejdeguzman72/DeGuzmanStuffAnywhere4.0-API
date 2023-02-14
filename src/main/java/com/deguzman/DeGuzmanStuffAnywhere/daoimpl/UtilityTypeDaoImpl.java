package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.deguzman.DeGuzmanStuffAnywhere.dao.UtilityTypeDao;
import com.deguzman.DeGuzmanStuffAnywhere.model.UtilityType;

@Repository
public class UtilityTypeDaoImpl implements UtilityTypeDao {

	String GET_ALL_UTILITY_TYPES = "SELECT * FROM UTILITY_TYPE";
	String GET_UTILITY_TYPE_BY_ID = "SELECT * FROM UTILITY_TYPE WHERE UTILITY_TYPE_ID = ?";
	String GET_UTILITY_TYPE_BY_DESCR = "SELECT * FROM UTILITY_TYPE WHERE UTILITY_TYPE_DESCR = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityTypeDaoImpl.class);

	@Override
	public List<UtilityType> findAllUtilityTypes() {
		List<UtilityType> list = new ArrayList<>();
		try {
			list = jdbcTemplate.query(GET_ALL_UTILITY_TYPES,
					BeanPropertyRowMapper.newInstance(UtilityType.class));

			LOGGER.info("Retrieving all utility types...");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ResponseEntity<UtilityType> findUtilityInformationById(int utility_type_id) {
		UtilityType type = null;
		try {
			type = jdbcTemplate.queryForObject(GET_UTILITY_TYPE_BY_ID,
					BeanPropertyRowMapper.newInstance(UtilityType.class), utility_type_id);

			LOGGER.info("Retrieving Utiltiy By ID: " + utility_type_id);
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok().body(type);
	}

	@Override
	public ResponseEntity<UtilityType> findUtilityInformationByDescr(String utility_type_descr) {
		UtilityType type = null;
		try {
			type = jdbcTemplate.queryForObject(GET_UTILITY_TYPE_BY_DESCR,
					BeanPropertyRowMapper.newInstance(UtilityType.class), utility_type_descr);

			LOGGER.info("Retrieving Utiltiy by descr: " + utility_type_descr);
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok().body(type);
	}

}
