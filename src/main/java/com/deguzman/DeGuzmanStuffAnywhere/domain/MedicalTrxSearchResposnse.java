package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalTransaction;

public class MedicalTrxSearchResposnse {

	public MedicalTransaction transaction;

	public MedicalTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(MedicalTransaction transaction) {
		this.transaction = transaction;
	}
}
