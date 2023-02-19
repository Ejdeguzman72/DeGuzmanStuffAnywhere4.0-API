package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.dto.MedicalTrxInfoDTO;

public class MedicalTrxSearchResposnse {

	public MedicalTrxInfoDTO transaction;

	public MedicalTrxInfoDTO getTransaction() {
		return transaction;
	}

	public void setTransaction(MedicalTrxInfoDTO transaction) {
		this.transaction = transaction;
	}
}
