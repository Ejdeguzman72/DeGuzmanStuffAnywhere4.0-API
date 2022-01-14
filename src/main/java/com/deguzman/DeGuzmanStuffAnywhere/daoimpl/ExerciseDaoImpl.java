package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.deguzman.DeGuzmanStuffAnywhere.dao.ExerciseDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.ExerciseInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Exercise;

@Repository
public class ExerciseDaoImpl implements ExerciseDao {

	String GET_ALL_EXERCISE_INFORMATION = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.NAME " + 
			"FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + 
			"WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID " + 
			"AND E.USER_ID = US.USER_ID";
	
	String GET_EXERCISE_INFORMATION_BY_USER = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.NAME " + 
			"FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + 
			"WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID " + 
			"AND E.USER_ID = US.USER_ID " +
			"AND E.USER_ID = ?";
	
	String GET_EXERCISE_INFORMATION_BY_TYPE = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.NAME " + 
			"FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + 
			"WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID " + 
			"AND E.USER_ID = US.USER_ID " +
			"AND E.EXERCISE_TYPE_ID = ?";
	
	String GET_EXERCISE_INFORMATION_BY_ID = "SELECT E.EXERCISE_ID, E.EXERCISE_NAME, E.DATE, E.REPS, E.SETS, E.WEIGHT, ET.EXERCISE_TYPE_NAME, US.NAME " + 
			"FROM EXERCISE E, EXERCISE_TYPE ET, USERS US " + 
			"WHERE E.EXERCISE_TYPE_ID = ET.EXERCISE_TYPE_ID " + 
			"AND E.USER_ID = US.USER_ID " +
			"AND E.EXERCISE_ID = ?";
	
	String ADD_EXERCISE_INFORMATION = "INSERT INTO EXERCISE " + 
			"(DATE, EXERCISE_NAME, REPS, SETS, WEIGHT, EXERCISE_TYPE_ID, USER_ID) " + 
			"VALUES(?, ?, ?, ?, ?, ?, ?)";
	
	String UPDATE_EXERCISE_INFORMATION = "UPDATE EXERCISE " +
			"SET DATE=?, EXERCISE_NAME=?, REPS=?, SETS=?, WEGIHT=?, EXERCISE_TYPE_ID, USER_ID=? " +
			"WHERE USER_ID =?";
	
	String DELETE_EXERCISE_INFORMATION_BY_ID = "DELETE FROM EXERCISE WHERE EXERCISE_ID =?";
	
	String DELETE_ALL_EXERCISE_INFORMATION = "DELETE FROM EXERCISE";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseDaoImpl.class);

	@Override
	public List<ExerciseInfoDTO> findAllExerciseInformation() {
		return jdbcTemplate.query(GET_ALL_EXERCISE_INFORMATION, BeanPropertyRowMapper.newInstance(ExerciseInfoDTO.class));
	}

	@Override
	public List<ExerciseInfoDTO> findExerciseInformationByUser(@PathVariable long user_id) {
		List<ExerciseInfoDTO> exerciseListUser = jdbcTemplate.query(GET_EXERCISE_INFORMATION_BY_USER, (rs,rowNum) -> 
			new ExerciseInfoDTO(
					rs.getInt("EXERCISE_ID"),
					rs.getString("EXERCISE_NAME"),
					rs.getInt("SETS"),
					rs.getInt("REPS"),
					rs.getDouble("WEIGHT"),
					rs.getString("DATE"),
					rs.getString("EXERCISE_TYPE_NAME"),
					rs.getString("NAME")
					), user_id);
		LOGGER.info("Retrieved Exercise Information by User ID: " + " " + user_id);
		
		return exerciseListUser;
	}

	@Override
	public List<ExerciseInfoDTO> findExerciseInformationByType(int exercise_type_id) {
		List<ExerciseInfoDTO> exerciseListType = jdbcTemplate.query(GET_EXERCISE_INFORMATION_BY_USER, (rs,rowNum) -> 
		new ExerciseInfoDTO(
				rs.getInt("EXERCISE_ID"),
				rs.getString("EXERCISE_NAME"),
				rs.getInt("SETS"),
				rs.getInt("REPS"),
				rs.getDouble("WEIGHT"),
				rs.getString("DATE"),
				rs.getString("EXERCISE_TYPE_NAME"),
				rs.getString("NAME")
				), exercise_type_id);
	LOGGER.info("Retrieved Exercise Information by Exercise Type ID: " + " " + exercise_type_id);
	
	return exerciseListType;
	}

	@Override
	public ResponseEntity<ExerciseInfoDTO> findExerciseById(@PathVariable int exercise_id) {
		ExerciseInfoDTO exerciseInfo = jdbcTemplate.queryForObject(GET_EXERCISE_INFORMATION_BY_ID, BeanPropertyRowMapper.newInstance(ExerciseInfoDTO.class), exercise_id);
		LOGGER.info("Retrieved Exercise information by exercise_id: " + " " + exercise_id);
		
		return ResponseEntity.ok().body(exerciseInfo);
	}
	
//	String ADD_EXERCISE_INFORMATION = "INSERT INTO EXERCISE " + 
//			"(DATE, EXERCISE_NAME, REPS, SETS, WEIGHT, EXERCISE_TYPE_ID, USER_ID) " + 
//			"VALUES(?, ?, ?, ?, ?, ?, ?)";
	
	@Override
	public int addExerciseInformation(Exercise exercise) {

		String date = exercise.getDate();
		String exerciseName = exercise.getExerciseName();
		int reps = exercise.getReps();
		int sets = exercise.getSets();
		double weight = exercise.getWeight();
		int exercise_type = exercise.getExercise_type_id();
		long user = exercise.getUser_id();
		
		LOGGER.info("Adding Exercise Entry for user with ID: " + user);
		
		return jdbcTemplate.update(ADD_EXERCISE_INFORMATION, new Object[] {
			date,
			exerciseName,
			reps,
			sets,
			weight,
			exercise_type,
			user
		});
	
	}

	@Override
	public int updateExerciseInformation(int exercise_id, Exercise exerciseDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteExerciseInformationById(int exercise_id) {
		return jdbcTemplate.update(DELETE_EXERCISE_INFORMATION_BY_ID, exercise_id);
	}

	@Override
	public int deleteAllExercisInformation() {
		return jdbcTemplate.update(DELETE_ALL_EXERCISE_INFORMATION);
	}
}