package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dao.AutoShopDao;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateAutoShopException;
import com.deguzman.DeGuzmanStuffAnywhere.model.AutoRepairShop;
@Repository
public class AutoRepairShopDaoImpl implements AutoShopDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoRepairShopDaoImpl.class);

	String GET_ALL_AUTO_REPAIR_SHOPS = "SELECT * FROM AUTO_SHOP ORDER BY AUTO_SHOP_NAME";
	String GET_AUTO_SHOP_BY_ID = "SELECT * FROM AUTO_SHOP WHERE AUTO_SHOP_ID = ?";
	String GET_AUTO_SHOP_BY_NAME = "SELECT * FROM AUTO_SHOP WHERE AUTO_SHOP_NAME = ?";
	String GET_AUTO_REPAIR_SHOP_BY_ZIP = "SELECT * FROM AUTO_SHOP WHERE ZIP = ?";
	String CHECK_DUPLICATE_AUTO_SHOPS = "SELECT * FROM AUTO_SHOP WHERE AUTO_SHOP_NAME = ?";
	String GET_COUNT_OF_REPAIR_SHOPS = "SELECT COUNT(*) FROM AUTO_SHOP";
	String ADD_AUTO_SHOP_INFORMATION = "INSERT INTO auto_shop " + "(address, auto_shop_name, city, state, zip) "
			+ "VALUES(?, ?, ?, ?, ?)";
	String UPDATE_AUTO_SHOP_INFORMATION = "UPDATE AUTO_SHOP SET AUTO_SHOP_NAME = ?, ADDRESS = ?, CITY = ?, STATE = ?, ZIP = ? WHERE AUTO_SHOP_ID = ?";
	String DELETE_AUTO_SHOP_INFORMATION_BY_ID = "DELETE FROM AUTO_SHOP WHERE AUTO_SHOP_ID = ?";
	String DELETE_ALL_AUTO_SHOPS = "DELETE FROM AUTO_SHOP";

	@Override
	@Cacheable(value = "autoShopList")
	public List<AutoRepairShop> findAllAutoRepairShopInfo() {
		List<AutoRepairShop> listAutoRepairShops = new ArrayList<>();
		try {
			listAutoRepairShops = jdbcTemplate.query(GET_ALL_AUTO_REPAIR_SHOPS,
					BeanPropertyRowMapper.newInstance(AutoRepairShop.class));

			LOGGER.info("Retrieving all auto repair shops...");
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return listAutoRepairShops;
	}

	@Override
	@Cacheable(value="autoShopById", key="#auto_shop_id")
	public ResponseEntity<AutoRepairShop> findAutoRepairShopById(int auto_shop_id) {
		AutoRepairShop autoShop = new AutoRepairShop();
		try {
			autoShop = jdbcTemplate.queryForObject(GET_AUTO_SHOP_BY_ID,
					BeanPropertyRowMapper.newInstance(AutoRepairShop.class), auto_shop_id);

			LOGGER.info("Retrieved Auto Repair Shop Information: " + autoShop.getAutoShopName() + "...");
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Error: " + e.toString());
		}
		

		return ResponseEntity.ok().body(autoShop);
	}

	@Override
	public ResponseEntity<AutoRepairShop> findAutoRepairShopByName(String autoShopName) {
		AutoRepairShop autoShop = new AutoRepairShop();
		try {
			autoShop = jdbcTemplate.queryForObject(GET_AUTO_SHOP_BY_NAME,
					BeanPropertyRowMapper.newInstance(AutoRepairShop.class), autoShopName);

			LOGGER.info("Retrieved Repair Shop Information: " + autoShop.getAutoShopName() + "...");

		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		return ResponseEntity.ok().body(autoShop);
	}

	@Override
	public List<AutoRepairShop> findAutoRepairShopByZip(String zip) {
		List<AutoRepairShop> autoShopList = new ArrayList<>();
		try {
			autoShopList = jdbcTemplate.query(GET_AUTO_REPAIR_SHOP_BY_ZIP,
					(rs, rowNum) -> new AutoRepairShop(rs.getInt("AUTO_SHOP_ID"), rs.getString("AUTO_SHOP_NAME"),
							rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIP")),
					zip);

			LOGGER.info("Retrieving Auto Shops By ZipCocde " + zip + "...");
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		

		return autoShopList;
	}

	@Override
	public long getCountOfAutoRepairShops() {
		long count = jdbcTemplate.queryForObject(GET_COUNT_OF_REPAIR_SHOPS, Integer.class);

		LOGGER.info("Getting count of Auto Repair Shops...");

		return count;
	}

	@Override
	@CachePut(value = "autoShopList")
	public int addAutoRepairShopInfo(AutoRepairShop autoShop) throws DuplicateAutoShopException {
		int count = 0;
		try {
			String autoShopName = autoShop.getAutoShopName();		
			String address = autoShop.getAddress();
			String city = autoShop.getCity();
			String state = autoShop.getState();
			String zip = autoShop.getZip();
			
			count = jdbcTemplate.update(ADD_AUTO_SHOP_INFORMATION, new Object[] {
					autoShopName,
					address,
					city,
					state,
					zip
			});
			
			if (checkAutoShops(autoShop)) {
				throw new DuplicateAutoShopException("Duplicate Auto Repair Shop");
			}

			LOGGER.info("Inserted Auto Repair Shop Information: " + autoShop.getAutoShopName() + "...");
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		 

		return count;

	}

	@Override
	@CachePut(value = "autoShopById", key = "#auto_shop_id")
	public int updateAutoShopInfo(int auto_shop_id, AutoRepairShop shopDetails) {
		
		int result = 0;
		
		AutoRepairShop autoShop = new AutoRepairShop();
		try {
			autoShop = jdbcTemplate.queryForObject(GET_AUTO_SHOP_BY_ID,
					BeanPropertyRowMapper.newInstance(AutoRepairShop.class), auto_shop_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		try {
			if (autoShop != null) {
				autoShop.setAutoShopName(shopDetails.getAutoShopName());
				autoShop.setAddress(shopDetails.getAddress());
				autoShop.setCity(shopDetails.getCity());
				autoShop.setState(shopDetails.getState());
				autoShop.setZip(shopDetails.getZip());
				autoShop.setAuto_shop_id(shopDetails.getAuto_shop_id());
				
				
				result = jdbcTemplate.update(UPDATE_AUTO_SHOP_INFORMATION, new Object[] {
					autoShop.getAutoShopName(),
					autoShop.getAddress(),
					autoShop.getCity(),
					autoShop.getState(),
					autoShop.getZip(),
					autoShop.getAuto_shop_id()
				});
				
				LOGGER.info("Updating Auto Repair Shop with auto_shop_id: " + auto_shop_id);
			}
		} catch (Exception e) {
			LOGGER.error("Error: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "autoShopById", key = "#auto_shop_id")
	public int deleteAutoRepairShopInfo(int auto_shop_id) {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_AUTO_SHOP_INFORMATION_BY_ID, auto_shop_id);
			
			LOGGER.info("Deleting Auto Repair Shop with ID: " + " " + auto_shop_id + "...");			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return count;
	}

	@Override
	@CachePut(value = "autoShopList")
	public int deleteAllAutoShop() {
		int count = jdbcTemplate.update(DELETE_ALL_AUTO_SHOPS);

		LOGGER.info("Deleting all Auto Repair Shops...");

		return count;
	}
	
	public boolean checkAutoShops(AutoRepairShop shop) {

		List<AutoRepairShop> shopList = findAllAutoRepairShopInfo();
		List<String> namesList;
		List<String> addressList;
		List<String> cityList;
		List<String> stateList;
		List<String> zipList;
		boolean duplicate = false;
		
		namesList = shopList.stream().map(AutoRepairShop::getAutoShopName).collect(Collectors.toList());
		addressList = shopList.stream().map(AutoRepairShop::getAddress).collect(Collectors.toList());
		cityList = shopList.stream().map(AutoRepairShop::getCity).collect(Collectors.toList());
		stateList = shopList.stream().map(AutoRepairShop::getState).collect(Collectors.toList());
		zipList = shopList.stream().map(AutoRepairShop::getZip).collect(Collectors.toList());
		
		
		if (namesList.contains(shop.autoShopName) &&
				addressList.contains(shop.address) &&
				cityList.contains(shop.city) &&
				stateList.contains(shop.state) &&
				zipList.contains(shop.zip)) {
			duplicate = true;
		}
		
		return duplicate;
	}

}
