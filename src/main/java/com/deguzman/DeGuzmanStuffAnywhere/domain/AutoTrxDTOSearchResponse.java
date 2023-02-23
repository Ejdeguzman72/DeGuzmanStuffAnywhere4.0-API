package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;

public class AutoTrxDTOSearchResponse {

	public AutoTrxInfoDTO transaction;

	public AutoTrxInfoDTO getTransaction() {
		return transaction;
	}

	public void setTransaction(AutoTrxInfoDTO transaction) {
		this.transaction = transaction;
	} 
}
