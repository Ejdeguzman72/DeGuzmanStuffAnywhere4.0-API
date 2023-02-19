package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.AutoTrxInfoDTO;

public class AutoTrxSearchResponse {

	public AutoTrxInfoDTO transaction;

	public AutoTrxInfoDTO getTransaction() {
		return transaction;
	}

	public void setTransaction(AutoTrxInfoDTO transaction) {
		this.transaction = transaction;
	} 
}
