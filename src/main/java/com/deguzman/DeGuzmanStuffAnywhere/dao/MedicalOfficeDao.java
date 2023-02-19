package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.deguzman.DeGuzmanStuffAnywhere.exception.DuplicateOfficeException;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice;

public interface MedicalOfficeDao {

	public List<MedicalOffice> findAllMedicalOfficeInformation();

	public List<MedicalOffice> findMedicalOfficesByZip(String zip);

	public ResponseEntity<MedicalOffice> findMedicalOfficeInformationById(long medicalOfficeId);

	public int getMedicalOfficeCount();

	public int addMedicalOfficeInformation(MedicalOffice medicalOffice) throws DuplicateOfficeException;

	public int updateMedicalOfficeInformation(long medicalOfficeId,
			MedicalOffice medicalOffice);

	public int deleteMedicalOfficeById(long medicalOfficeId);

	public int deleteAllMedicalOfficeInformation();

}
