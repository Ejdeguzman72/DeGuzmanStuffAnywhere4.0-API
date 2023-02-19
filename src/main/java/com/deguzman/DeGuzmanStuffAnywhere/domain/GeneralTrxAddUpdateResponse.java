package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.GeneralTransaction;

public class GeneralTrxAddUpdateResponse {
	GeneralTransaction transaction;

	public GeneralTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(GeneralTransaction transaction) {
		this.transaction = transaction;
	}

}
