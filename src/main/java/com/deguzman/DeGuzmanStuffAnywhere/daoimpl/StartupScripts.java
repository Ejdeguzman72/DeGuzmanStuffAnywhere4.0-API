package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deguzman.DeGuzmanStuffAnywhere.model.ExerciseType;
import com.deguzman.DeGuzmanStuffAnywhere.model.RestaurantType;
import com.deguzman.DeGuzmanStuffAnywhere.model.TransactionType;

@Repository
public class StartupScripts {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private ExerciseTypeDaoImpl exerciseTypeDaoImpl;
	
	@Autowired
	private RestaurantTypeDaoImpl restaurantTypeDaoImpl;
	
	@Autowired 
	private TransactionTypeDaoImpl transactionTypeDaoImpl;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(StartupScripts.class);
	
	public String INSERT_EXERCISE_TYPES = "INSERT INTO EXERCISE_TYPE(EXERCISE_TYPE_NAME) VALUES('Abs'), ('Back'), ('Biceps'), ('Chest'), ('Delts'),('Legs'), ('Shoulders'),('Triceps')";
	
	public String INSERT_RESTAURANT_TYPES = "INSERT INTO RESTAURANT_TYPE (DESCR) VALUES ('AMERICAN'),('PIZZERIA'),('CHINESE'),('HISPANIC'),('TEXMEX'),('BREAKFEAST'),('PASTA'),('STEAKHOUSE'),('SUSHI'),('RAMEN'),('PHO'),('FAST FOOD')";
	
	public String INSERT_TRANSACTION_TYPES = "INSERT INTO TRANSACTION_TYPE(TRANSACTION_TYPE_DESCR) "
			+ "VALUES('RENT'),('GAS'),('CABLE'),('ELECTRIC'),('INTERNET'),('PHONES'),('INTERNET'),('MORTGAGE'),"
			+ "('INSURANCE - HOMEOWNERS'),('INSRUANCE - AUTOMOBILE'),('INSRUANCE - LIFE'),('GROCERIES'),('RESTAURANT'),"
			+ "('SCHOOL SUPPLIES'),('SOCIAL'),('VACATION'),('AUTO - OIL CHANGE'),('AUTO - BRAKES'),('AUTO - BODY WORK'),"
			+ "('AUTO - TIRES'),('AUTO - CAR DETAILS'),('AUTO - GENEAL MAINTENANCE'),(' AUTO - CRASH MAINTENANCE'),('MEDICAL - COPAY'),"
			+ "('MEDICAL - PRESCRIPTION'), ('MEDICAL - HOSPITAL BILLS')";
	
	public int startUpExerciseTypes() {
		try {
			List<ExerciseType> list = exerciseTypeDaoImpl.findAllExerciseTypeInformation();
			int result = 0;
			
			if (list.size() == 0) {
				result = jdbcTemplate.update(INSERT_EXERCISE_TYPES);
				
				LOGGER.info("List is empty, inserting records now...");
			}
			
			return result;
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
			e.printStackTrace();
		}
		return 0;
	}
	
	public int startupRestaurantTypes() {
		List<RestaurantType> list = restaurantTypeDaoImpl.findAllRestaurantTypeInformation();
		int result = 0;
		
		if (list.size() == 0) {
			result = jdbcTemplate.update(INSERT_RESTAURANT_TYPES);
			
			LOGGER.info("List is empty, inserting records now...");
		}
		
		return result;
	}
	
	public int startupTransactionTypes() {
		List<TransactionType> list = transactionTypeDaoImpl.retrieveAllTransactionTypes();
		int result = 0;
		
		if (list.size() == 0) {	
			result = jdbcTemplate.update(INSERT_TRANSACTION_TYPES);
			
			LOGGER.info("List is empty, inserting records now...");
		}
		
		return result;
	}
	
}
