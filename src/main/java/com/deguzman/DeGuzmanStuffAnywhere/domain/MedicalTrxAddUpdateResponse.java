package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction;

public class MedicalTrxAddUpdateResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3951937168597244251L;

	MedicalTransaction transaction;

	public MedicalTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(MedicalTransaction transaction) {
		this.transaction = transaction;
	}
}
