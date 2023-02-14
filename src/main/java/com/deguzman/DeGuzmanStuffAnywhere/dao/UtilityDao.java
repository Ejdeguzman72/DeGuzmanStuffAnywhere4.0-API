package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Utility;

public interface UtilityDao {

	public List<UtilityInfoDTO> findAllUtilityInformation();

	public List<UtilityInfoDTO> findUtilityInformationByDueDate(String dueDate);

	public ResponseEntity<UtilityInfoDTO> findUtilityInformationById(long utility_id);

	public ResponseEntity<UtilityInfoDTO> findUtilityInformationByName(String name);

	public ResponseEntity<UtilityInfoDTO> findUtilityInformationByType(int utility_type_id);

	public long findUtilityCount();

	public int addUtilityInformation(Utility utility);

	public int updateUtilityInformation(long utility_id, Utility utilityDetails);

	public int deleteUtilityInformation(long utility_id);

	public int deleteAllUtilityInformation();

}
