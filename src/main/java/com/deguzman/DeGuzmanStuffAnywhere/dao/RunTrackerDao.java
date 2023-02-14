package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker;

public interface RunTrackerDao {

	public List<RunTrackerInfoDTO> findAllRunTrackerInformation();

	public List<RunTrackerInfoDTO> findRunTrackerInformationByUser(long user_id);

	public ResponseEntity<RunTrackerInfoDTO> findRunTrackerInformationDTOById(long run_id);
	
	public ResponseEntity<RunTracker> findRunTrackerById(long run_id);

	public long findCountOfRunTrackerInformation();

	public int addRunTrackerInformation(RunTracker runTracker);

	public int updateRunTrackerInformation(long run_id, RunTracker runTrackerDetails);

	public int deleteRunTrackerInformation(long run_id);

	public int deleteAllRunTrackerInformation();

}
