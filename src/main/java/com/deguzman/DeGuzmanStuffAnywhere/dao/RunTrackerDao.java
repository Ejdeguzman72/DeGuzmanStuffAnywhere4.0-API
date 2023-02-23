package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.RunTracker;

public interface RunTrackerDao {

	public List<RunTrackerInfoDTO> findAllRunTrackerInformation();

	public List<RunTrackerInfoDTO> findRunTrackerInformationByUser(long user_id);

	public RunTrackerInfoDTO findRunTrackerInformationDTOById(long run_id);
	
	public RunTracker findRunTrackerById(long run_id);

	public long findCountOfRunTrackerInformation();

	public int addRunTrackerInformation(RunTracker runTracker);

	public int updateRunTrackerInformation(long run_id, RunTracker runTrackerDetails);

	public int deleteRunTrackerInformation(long run_id);

	public int deleteAllRunTrackerInformation();

}
