package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction;

public class AutoTrxAddUpdateResponse {

	AutoTransaction transaction;

	public AutoTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(AutoTransaction transaction) {
		this.transaction = transaction;
	}
}
