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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deguzman.DeGuzmanStuffAnywhere.dao.RunTrackerDao;
import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker;

@RestController
@RequestMapping("/app/run-tracker")
@CrossOrigin
public class RunTrackerDaoImpl implements RunTrackerDao {

	String GET_ALL_RUN_TRACKER_INFO = "SELECT R.RUN_ID, R.RUN_DATE,R.RUN_DISTANCE, R.RUN_TIME, US.USERNAME "
			+ "FROM RUN_TRACKER R, USERS US " + "WHERE R.USER_ID = US.USER_ID " + "ORDER BY R.RUN_DATE DESC";

	String GET_RUN_TRACKER_BY_USER = "SELECT R.RUN_ID, R.RUN_DATE,R.RUN_DISTANCE, R.RUN_TIME, US.USERNAME "
			+ "FROM RUN_TRACKER R, USERS US " + "WHERE R.USER_ID = US.USER_ID " + "AND R.USER_ID = ? "
			+ "ORDER BY R.RUN_DATE DESC";

	String GET_RUN_TRACKER_INFORMATION_BY_ID = "SELECT R.RUN_ID, R.RUN_DATE,R.RUN_DISTANCE, R.RUN_TIME, US.USERNAME "
			+ "FROM RUN_TRACKER R, USERS US " + "WHERE R.USER_ID = US.USER_ID " + "AND R.RUN_ID = ?";
	
	String GET_RUN_TRACKER_INFO = "SELECT * FROM RUN_TRACKER WHERE RUN_ID = ?";

	String GET_RUN_COUNT = "SELECT COUNT(*) FROM RUN_TRACKER";

	String ADD_RUN_TRACKER_INFORMATION = "INSERT INTO RUN_TRACKER (RUN_DATE,RUN_DISTANCE,RUN_TIME,USER_ID) "
			+ "VALUES (?,?,?,?)";
	
	String UPDATE_RUN_TRACKER_INFORMATION = "UPDATE RUN_TRACKER "
			+ "SET RUN_DATE=?, "
			+ "RUN_DISTANCE=?, "
			+ "RUN_TIME=?, "
			+ "USER_ID=? "
			+ "WHERE RUN_ID = ?";

	String DELETE_RUN_TRACKER_INFORMATION_BY_ID = "DELETE FROM RUN_TRACKER WHERE RUN_ID = ?";

	String DELETE_ALL_RUN_TRACKER_INFORMATION = "DELETE FROM RUN_TRACKER";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(RunTrackerDaoImpl.class);

	@Override
	@Cacheable(value = "runList")
	public List<RunTrackerInfoDTO> findAllRunTrackerInformation() {
		List<RunTrackerInfoDTO> list = new ArrayList<>();
		
		try {
			list = jdbcTemplate.query(GET_ALL_RUN_TRACKER_INFO,
					BeanPropertyRowMapper.newInstance(RunTrackerInfoDTO.class));
			
			LOGGER.info("Retrieving all run tracker entries...");			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return list;
	}

	@Override
	public List<RunTrackerInfoDTO> findRunTrackerInformationByUser(long user_id) {
		List<RunTrackerInfoDTO> runListUser = new ArrayList<>();
		
		try {
			runListUser = jdbcTemplate.query(GET_RUN_TRACKER_BY_USER,
					(rs, rowNum) -> new RunTrackerInfoDTO(rs.getInt("RUN_ID"), rs.getString("RUN_DATE"),
							rs.getDouble("RUN_DISTANCE"), rs.getString("RUN_TIME"), rs.getString("USERNAME")),
					user_id);
			
			LOGGER.info("Retrieving all run information based on user_id: " + user_id);			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return runListUser;
	}

	@Override
	@Cacheable(value = "runById", key = "#run_id")
	public RunTrackerInfoDTO findRunTrackerInformationDTOById(long run_id) {
		RunTrackerInfoDTO runTrackerInfo = new RunTrackerInfoDTO();
		
		try {
			runTrackerInfo = jdbcTemplate.queryForObject(GET_RUN_TRACKER_INFORMATION_BY_ID,
					BeanPropertyRowMapper.newInstance(RunTrackerInfoDTO.class), run_id);
			
			LOGGER.info("Retrieving Run Tracker Information By Run ID: " + " " + run_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return runTrackerInfo;
	}
	
	@Override
	public RunTracker findRunTrackerById(long run_id) {
		RunTracker runTrackerInfo = new RunTracker();
		
		try {
			runTrackerInfo = jdbcTemplate.queryForObject(GET_RUN_TRACKER_INFO,
					BeanPropertyRowMapper.newInstance(RunTracker.class), run_id);
			
			LOGGER.info("Retrieving Run Tracker Information By Run ID: " + " " + run_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return runTrackerInfo;
	}

	@Override
	public long findCountOfRunTrackerInformation() {
		long count = jdbcTemplate.queryForObject(GET_RUN_COUNT, Integer.class);

		LOGGER.info("Getting run tracker count");

		return count;
	}

	@Override
	@CachePut(value = "runList")
	public int addRunTrackerInformation(RunTracker runTracker) {
		int result = 0;
		
		try {
			String run_date = runTracker.getRunDate();
			double run_distance = runTracker.getRunDistance();
			String run_time = runTracker.getRunTime();
			long user = runTracker.getUser_id();
			
			LOGGER.info("Adding run information: " + run_date + " " + run_distance);
			
			result = jdbcTemplate.update(ADD_RUN_TRACKER_INFORMATION,
					new Object[] { run_date, run_distance, run_time, user });			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result;
	}

	@Override
	@CachePut(value = "runById", key = "#run_id")
	public int updateRunTrackerInformation(long run_id, RunTracker runTrackerDetails) {

		int result = 0;
		RunTracker run = new RunTracker();
		
		try {
			run = jdbcTemplate.queryForObject(GET_RUN_TRACKER_INFO, 
					BeanPropertyRowMapper.newInstance(RunTracker.class), run_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}
		
		try {
			if (run != null) {
				run.setRunDate(runTrackerDetails.getRunDate());
				run.setRunDistance(runTrackerDetails.getRunDistance());
				run.setRunTime(runTrackerDetails.getRunTime());
				run.setUser_id(runTrackerDetails.getUser_id());
				run.setRun_id(run_id);
				
				
				result = jdbcTemplate.update(UPDATE_RUN_TRACKER_INFORMATION, new Object[] {
						run.getRunDate(),
						run.getRunDistance(),
						run.getRunTime(),
						run.getUser_id(),
						run.getRun_id()
				});
				
				LOGGER.info("Updating run tracker info for run_id: " + run_id);
			}			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}
		
		return result; 
	}

	@Override
	@CachePut(value = "runById", key = "#run_id")
	public int deleteRunTrackerInformation(long run_id) {
		int count = 0;
		
		try {
			count = jdbcTemplate.update(DELETE_RUN_TRACKER_INFORMATION_BY_ID, run_id);
			
			LOGGER.info("Delete run information by ID: " + run_id);			
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error("Empty data set: " + e.toString());
		}

		return count;
	}

	@Override
	@CachePut(value = "runList")
	public int deleteAllRunTrackerInformation() {
		int count = 0;
		
		try {
			count = jdbcTemplate.queryForObject(DELETE_ALL_RUN_TRACKER_INFORMATION, Integer.class);
			
			LOGGER.info("Deleting all run information...");			
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.toString());
		}

		return count;
	}

}
