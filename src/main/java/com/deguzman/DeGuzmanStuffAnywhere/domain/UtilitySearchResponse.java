package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;

public class UtilitySearchResponse {

	public UtilityInfoDTO utility;

	public UtilityInfoDTO getUtility() {
		return utility;
	}

	public void setUtility(UtilityInfoDTO utility) {
		this.utility = utility;
	}
}
