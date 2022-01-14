package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.dao.RunTrackerDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker;

@RestController
@RequestMapping("/app/run-tracker")
@CrossOrigin
public class RunTrackerDaoImpl implements RunTrackerDao {

	String GET_ALL_RUN_TRACKER_INFO = "SELECT R.RUN_ID, R.RUN_DATE,R.RUN_DISTANCE, R.RUN_TIME, US.NAME " + 
			"FROM RUN_TRACKER R, USERS US " + 
			"WHERE R.USER_ID = US.USER_ID";
	
	String GET_RUN_TRACKER_BY_USER = "SELECT R.RUN_ID, R.RUN_DATE,R.RUN_DISTANCE, R.RUN_TIME, US.NAME " + 
			"FROM RUN_TRACKER R, USERS US " + 
			"WHERE R.USER_ID = US.USER_ID " +
			"AND R.USER_ID = ?";
	
	String GET_RUN_TRACKER_INFORMATION_BY_ID = "SELECT R.RUN_ID, R.RUN_DATE,R.RUN_DISTANCE, R.RUN_TIME, US.NAME " + 
			"FROM RUN_TRACKER R, USERS US " + 
			"WHERE R.USER_ID = US.USER_ID " +
			"AND R.RUN_ID = ?";
	
	String GET_RUN_COUNT = "SELECT COUNT(*) FROM RUN_TRACKER";
	
	String ADD_RUN_TRACKER_INFORMATION = "INSERT INTO RUN_TRACKER (RUN_DATE,RUN_DISTANCE,RUN_TIME,USER_ID) " + 
			"VALUES (?,?,?,?)";
	
	String DELETE_RUN_TRACKER_INFORMATION_BY_ID = "DELETE FROM RUN_TRACKER WHERE RUN_ID = ?";
	
	String DELETE_ALL_RUN_TRACKER_INFORMATION = "DELETE FROM RUN_TRACKER";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RunTrackerDaoImpl.class);

	@Override
	public List<RunTrackerInfoDTO> findAllRunTrackerInformation() {
		return jdbcTemplate.query(GET_ALL_RUN_TRACKER_INFO, BeanPropertyRowMapper.newInstance(RunTrackerInfoDTO.class));
	}

	@Override
	public List<RunTrackerInfoDTO> findRunTrackerInformationByUser(@PathVariable long user_id) {
		return jdbcTemplate.query(GET_RUN_TRACKER_BY_USER, (rs,rowNum) -> 
				new RunTrackerInfoDTO(
						rs.getInt("RUN_ID"),
						rs.getString("RUN_DATE"),
						rs.getDouble("RUN_DISTANCE"),
						rs.getString("RUN_TIME"),
						rs.getString("NAME")
						), user_id);
	}

	@Override
	public ResponseEntity<RunTrackerInfoDTO> findRunTrackerInformationById(@PathVariable long run_id) {
		RunTrackerInfoDTO runTrackerInfo = jdbcTemplate.queryForObject(GET_RUN_TRACKER_INFORMATION_BY_ID, BeanPropertyRowMapper.newInstance(RunTrackerInfoDTO.class), run_id);
		LOGGER.info("Retrieving Run Tracker Information By Run ID: " + " " + run_id);
		
		return ResponseEntity.ok().body(runTrackerInfo);
	}
	
	@Override
	public long findCountOfRunTrackerInformation() {
		return jdbcTemplate.queryForObject(GET_RUN_COUNT, Integer.class);
	}

	@Override
	public int addRunTrackerInformation(RunTracker runTracker) {
		
		String run_date = runTracker.getRunDate();
		double run_distance = runTracker.getRunDistance();
		String run_time = runTracker.getRunTime();
		long user = runTracker.getUser_id();
		
		return jdbcTemplate.update(ADD_RUN_TRACKER_INFORMATION, new Object[] {
				run_date,
				run_distance,
				run_time,
				user
		});
	}

	@Override
	public int updateRunTrackerInformation(long run_id, RunTracker runTrackerDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRunTrackerInformation(@PathVariable long run_id) {
		return jdbcTemplate.update(DELETE_RUN_TRACKER_INFORMATION_BY_ID, run_id);
	}

	@Override
	public int deleteAllRunTrackerInformation() {
		return jdbcTemplate.queryForObject(DELETE_ALL_RUN_TRACKER_INFORMATION, Integer.class);
	}
	
}