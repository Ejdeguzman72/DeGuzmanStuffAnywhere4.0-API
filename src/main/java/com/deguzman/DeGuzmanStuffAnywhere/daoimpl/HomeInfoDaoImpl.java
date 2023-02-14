package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deguzman.DeGuzmanStuffAnywhere.dao.HomeInfoDao;
import com.deguzman.DeGuzmanStuffAnywhere.model.HomeInfo;

@Repository
public class HomeInfoDaoImpl implements HomeInfoDao {

	String GET_HOME_INFO = "SELECT * FROM HOME";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeInfoDaoImpl.class);

	@Override
	@Cacheable(value = "homeInfoList")
	public List<HomeInfo> getAllHomeInfo() {
		List<HomeInfo> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_HOME_INFO, BeanPropertyRowMapper.newInstance(HomeInfo.class));
			
			LOGGER.info("Getting All Home Info...");
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return list;
	}

}
