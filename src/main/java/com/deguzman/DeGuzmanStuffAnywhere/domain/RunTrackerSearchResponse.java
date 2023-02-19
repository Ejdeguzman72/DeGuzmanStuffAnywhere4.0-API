package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.RunTrackerInfoDTO;

public class RunTrackerSearchResponse {

	public RunTrackerInfoDTO run;

	public RunTrackerInfoDTO getRun() {
		return run;
	}

	public void setRun(RunTrackerInfoDTO run) {
		this.run = run;
	}
}
