package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.GeneralTrxInfoDTO;

public class GeneralTrxDTOSearchResponse {

	public GeneralTrxInfoDTO transaction;

	public GeneralTrxInfoDTO getTransaction() {
		return transaction;
	}

	public void setTransaction(GeneralTrxInfoDTO transaction) {
		this.transaction = transaction;
	}
}
