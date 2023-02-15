package com.deguzman.DeGuzmanStuffAnywhere.domain;

import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice;

public class MedicalOfficeAddUpdateResponse extends MedicalOffice {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2104151972228270108L;

	MedicalOffice office;

	public MedicalOffice getOffice() {
		return office;
	}

	public void setOffice(MedicalOffice office) {
		this.office = office;
	}
}
