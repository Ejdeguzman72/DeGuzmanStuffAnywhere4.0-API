package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.deguzman.DeGuzmanStuffAnywhere.dao.ExerciseDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.ExerciseInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Exercise;

@Repository
public class ExerciseDaoImpl implements ExerciseDao {

	String GET_ALL_EXERCISE_INFORMATION = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.USERNAME "
			+ "FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + "WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID "
			+ "AND E.USER_ID = US.USER_ID";

	String GET_EXERCISE_INFORMATION_BY_USER = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.USERNAME "
			+ "FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + "WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID "
			+ "AND E.USER_ID = US.USER_ID " + "AND E.USER_ID = ?";

	String GET_EXERCISE_INFORMATION_BY_TYPE = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.USERNAME "
			+ "FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + "WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID "
			+ "AND E.USER_ID = US.USER_ID " + "AND E.EXERCISE_TYPE_ID = ?";

	String GET_EXERCISE_INFORMATION_BY_ID = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.USERNAME "
			+ "FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + "WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID "
			+ "AND E.USER_ID = US.USER_ID " + "AND E.EXERCISE_ID = ?";
	
	String GET_EXERCISE_INFO = "SELECT * FROM EXERCISE WHERE EXERCISE_ID = ?";

	String ADD_EXERCISE_INFORMATION = "INSERT INTO EXERCISE "
			+ "(DATE, EXERCISE_NAME, REPS, SETS, WEIGHT, EXERCISE_TYPE_ID, USER_ID) " + "VALUES(?, ?, ?, ?, ?, ?, ?)";

	String UPDATE_EXERCISE_INFORMATION = "UPDATE EXERCISE "
			+ "SET EXERCISE_NAME=?, SETS=?, REPS=?, WEIGHT=?, DATE=?, EXERCISE_TYPE_ID=?, USER_ID=? "
			+ "WHERE EXERCISE_ID=?";

	String DELETE_EXERCISE_INFORMATION_BY_ID = "DELETE FROM EXERCISE WHERE EXERCISE_ID =?";

	String DELETE_ALL_EXERCISE_INFORMATION = "DELETE FROM EXERCISE";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseDaoImpl.class);

	@Override
	@Cacheable(value = "exerciseList")
	public List<ExerciseInfoDTO> findAllExerciseInformation() {
		List<ExerciseInfoDTO> list = new ArrayList<>();
		try {
			list = jdbcTemplate.query(GET_ALL_EXERCISE_INFORMATION,
					BeanPropertyRowMapper.newInstance(ExerciseInfoDTO.class));
			
			LOGGER.info("Retrieving All Exercise Information...");			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return list;
	}

	@Override
	public List<ExerciseInfoDTO> findExerciseInformationByUser(long user_id) {
		List<ExerciseInfoDTO> exerciseListUser = new ArrayList<>();
		
		try {
			exerciseListUser = jdbcTemplate.query(GET_EXERCISE_INFORMATION_BY_USER,
					(rs, rowNum) -> new ExerciseInfoDTO(rs.getInt("EXERCISE_ID"), rs.getString("EXERCISE_NAME"),
							rs.getInt("SETS"), rs.getInt("REPS"), rs.getDouble("WEIGHT"), rs.getString("DATE"),
							rs.getString("EXERCISE_TYPE_NAME"), rs.getString("USERNAME")),
					user_id);
			
			LOGGER.info("Retrieved Exercise Information by User ID: " + " " + user_id);			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return exerciseListUser;
	}

	@Override
	public List<ExerciseInfoDTO> findExerciseInformationByType(int exercise_type_id) {
		List<ExerciseInfoDTO> exerciseListType = new ArrayList<>();
		
		try {
			exerciseListType = jdbcTemplate.query(GET_EXERCISE_INFORMATION_BY_USER,
					(rs, rowNum) -> new ExerciseInfoDTO(rs.getInt("EXERCISE_ID"), rs.getString("EXERCISE_NAME"),
							rs.getInt("SETS"), rs.getInt("REPS"), rs.getDouble("WEIGHT"), rs.getString("DATE"),
							rs.getString("EXERCISE_TYPE_NAME"), rs.getString("USERNAME")),
					exercise_type_id);

			LOGGER.info("Retrieved Exercise Information by Exercise Type ID: " + " " + exercise_type_id);
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return exerciseListType;
	}

	@Override
	@Cacheable(value = "exerciseById", key = "#exercise_id")
	public ResponseEntity<Exercise> findExerciseById(int exercise_id) {
		Exercise exerciseInfo = new Exercise();
		
		try {
			exerciseInfo = jdbcTemplate.queryForObject(GET_EXERCISE_INFO,
					BeanPropertyRowMapper.newInstance(Exercise.class), exercise_id);
			
			LOGGER.info("Retrieved Exercise information by exercise_id: " + " " + exercise_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return ResponseEntity.ok().body(exerciseInfo);
	}
	
	@Override
	public ResponseEntity<ExerciseInfoDTO> findExerciseDTOById(int exercise_id) {
		ExerciseInfoDTO exerciseInfo = new ExerciseInfoDTO();
		
		try {
			exerciseInfo = jdbcTemplate.queryForObject(GET_EXERCISE_INFORMATION_BY_ID,
					BeanPropertyRowMapper.newInstance(ExerciseInfoDTO.class), exercise_id);
			
			LOGGER.info("Retrieved Exercise information by exercise_id: " + " " + exercise_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return ResponseEntity.ok().body(exerciseInfo);
	}
	
	@Override
	@CachePut(value = "exerciseList")
	public int addExerciseInformation(Exercise exercise) {

		int result = 0;
		
		try {
			String exerciseName = exercise.getExerciseName();
			int sets = exercise.getSets();
			int reps = exercise.getReps();
			double weight = exercise.getWeight();
			String date = exercise.getDate();
			int exercise_type = exercise.getExercise_type_id();
			long user = exercise.getUser_id();

			LOGGER.info("Adding Exercise Entry for user with ID: " + user);

			result = jdbcTemplate.update(ADD_EXERCISE_INFORMATION,
					new Object[] { date, exerciseName, reps, sets, weight, exercise_type, user });
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "exerciseById", key = "#exercise_id")
	public int updateExerciseInformation(int exercise_id, Exercise exerciseDetails) {

		int result = 0;
		Exercise exercise = new Exercise();
		
		try {
			exercise = jdbcTemplate.queryForObject(GET_EXERCISE_INFO,
					BeanPropertyRowMapper.newInstance(Exercise.class), exercise_id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		try {
			if (exercise != null) {
				exercise.setExerciseName(exerciseDetails.getExerciseName());
				exercise.setSets(exerciseDetails.getSets());
				exercise.setReps(exerciseDetails.getReps());
				exercise.setWeight(exerciseDetails.getWeight());
				exercise.setDate(exerciseDetails.getDate());
				exercise.setExercise_type_id(exerciseDetails.getExercise_type_id());
				exercise.setUser_id(exerciseDetails.getUser_id());
				exercise.setExercise_id(exercise_id);
				
				result = jdbcTemplate.update(UPDATE_EXERCISE_INFORMATION, new Object[] {
					exercise.getExerciseName(),
					exercise.getSets(),
					exercise.getReps(),
					exercise.getWeight(),
					exercise.getDate(),
					exercise.getExercise_type_id(),
					exercise.getUser_id(),
					exercise.getExercise_id()
				});
				
				LOGGER.info("Updating exercise information with exericse_id: " + exercise_id);
			}
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "exerciseById", key = "#exercise_id")
	public int deleteExerciseInformationById(int exercise_id) {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_EXERCISE_INFORMATION_BY_ID, exercise_id);
			
			LOGGER.info("Deleting Exercise Information by exercise_id: " + exercise_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return count;
	}

	@Override
	@CachePut(value = "exerciseList")
	public int deleteAllExercisInformation() {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_ALL_EXERCISE_INFORMATION);
			
			LOGGER.info("Deleting All Exercise Information...");
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return count;
	}
}
