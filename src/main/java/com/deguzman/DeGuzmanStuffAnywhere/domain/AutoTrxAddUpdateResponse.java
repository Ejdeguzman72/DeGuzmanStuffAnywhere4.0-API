package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.AutoTransaction;

public class AutoTrxAddUpdateResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3931117059867183295L;

	AutoTransaction transaction;

	public AutoTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(AutoTransaction transaction) {
		this.transaction = transaction;
	}
}
