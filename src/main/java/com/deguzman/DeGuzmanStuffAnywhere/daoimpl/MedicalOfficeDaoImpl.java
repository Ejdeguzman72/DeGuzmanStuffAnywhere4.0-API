package com.deguzman.DeGuzmanStuffAnywhere.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.deguzman.DeGuzmanStuffAnywhere.dao.MedicalOfficeDao;
import com.deguzman.DeGuzmanStuffAnywhere.model.MedicalOffice;

@Repository
public class MedicalOfficeDaoImpl implements MedicalOfficeDao {

	String GET_ALL_MEDICAL_OFFICE_INFORMATION = "SELECT * FROM MEDICAL_OFFICE";
	String GET_MEDICAL_OFFICE_INFORMATION_BY_ZIP = "SELECT * FROM MEDICAL_OFFICE WHERE ZIP = ?";
	String GET_ALL_MEDICAL_OFFICE_INFORMATION_BY_ID = "SELECT * FROM MEDICAL_OFFICE WHERE MEDICAL_OFFICE_ID = ?";
	String GET_MEDICAL_OFFICE_COUNT = "SELECT COUNT(*) FROM MEDICAL_OFFICE";
	String ADD_MEDICAL_OFFICE_INFORMATION = "INSERT INTO MEDICAL_OFFICE " + 
			"(ADDRESS, CITY, NAME, STATE, ZIP) " + 
			"VALUES(?, ?, ?, ?, ?)";
	String UPDATE_MEDICAL_OFFICE_INFORMATION = "UPDATE MEDICAL_OFFICE " + 
			"SET ADDRESS=?, CITY=?, NAME=?, STATE=?, ZIP=?" + 
			"WHERE MEDICAL_OFFICE_ID=?";
	String DELETE_MEDICAL_OFFICE_BY_ID = "DELETE FROM MEDICAL_OFFICE WHERE MEDICAL_OFFICE_ID = ?";
	String DELETE_ALL_MEDICAL_OFFICE_INFORMATION = "DELETE FROM MEDICAL_OFFICE"; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MedicalOfficeDaoImpl.class);
	
	@Override
	public List<MedicalOffice> findAllMedicalOfficeInformation() {
		return jdbcTemplate.query(GET_ALL_MEDICAL_OFFICE_INFORMATION, BeanPropertyRowMapper.newInstance(MedicalOffice.class));
	}

	@Override
	public List<MedicalOffice> findMedicalOfficesByZip(String zip) {
		List<MedicalOffice> officeList = jdbcTemplate.query(GET_MEDICAL_OFFICE_INFORMATION_BY_ZIP, (rs, rowNum) ->
			new MedicalOffice(
					rs.getInt("MEDICAL_OFFICE_ID"),
					rs.getString("NAME"),
					rs.getString("ADDRESS"),
					rs.getString("CITY"),
					rs.getString("STATE"),
					rs.getString("ZIP")
					), zip);
		
		return officeList;
	}

	@Override
	public ResponseEntity<MedicalOffice> findMedicalOfficeInformationById(long medicalOfficeId) {
		MedicalOffice medicalOffice = jdbcTemplate.queryForObject(GET_ALL_MEDICAL_OFFICE_INFORMATION_BY_ID, BeanPropertyRowMapper.newInstance(MedicalOffice.class), medicalOfficeId);
		LOGGER.info("Retrieved Medical Office Information: " + " " + medicalOffice.getName());
		
		return ResponseEntity.ok().body(medicalOffice);
	}

	@Override
	public int getMedicalOfficeCount() {
		return jdbcTemplate.queryForObject(GET_MEDICAL_OFFICE_COUNT, Integer.class);
	}

	@Override
	public int addMedicalOfficeInformation(MedicalOffice medicalOffice) {
		return jdbcTemplate.update(ADD_MEDICAL_OFFICE_INFORMATION, new Object[] {
			medicalOffice.getAddress(),medicalOffice.getCity(),medicalOffice.getName(),medicalOffice.getState(),medicalOffice.getZip()	
		});
	}

	@Override
	public int updateMedicalOfficeInformation(long medicalOfficeId, MedicalOffice medicalOffice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMedicalOfficeById(long medicalOfficeId) {
		return jdbcTemplate.update(DELETE_MEDICAL_OFFICE_BY_ID, medicalOfficeId);
	}

	@Override
	public int deleteAllMedicalOfficeInformation() {
		return jdbcTemplate.update(DELETE_ALL_MEDICAL_OFFICE_INFORMATION);
	}

}