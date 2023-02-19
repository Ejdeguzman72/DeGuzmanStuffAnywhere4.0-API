package com.deguzman.DeGuzmanStuffAnywhere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.deguzman.DeGuzmanStuffAnywhere.daoimpl.UtilityDaoImpl;
import com.deguzman.DeGuzmanStuffAnywhere.domain.DeleteAllResponse;
import com.deguzman.DeGuzmanStuffAnywhere.domain.UtilityListResponse;
import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Utility;

@Service
public class UtilityService {

	@Autowired
	private UtilityDaoImpl utilityDaoImpl;
	
	public UtilityListResponse findAllUtilityInformation() {
		UtilityListResponse response = new UtilityListResponse();
		List<UtilityInfoDTO> list = utilityDaoImpl.findAllUtilityInformation();
		
		response.setList(list);
		return response;
	}
	
	public UtilityListResponse findUtilityInformationByDueDate(String dueDate) {
		UtilityListResponse response = new UtilityListResponse();
		List<UtilityInfoDTO> list = utilityDaoImpl.findUtilityInformationByDueDate(dueDate);
		
		response.setList(list);
		return response;
	}
	
	public ResponseEntity<UtilityInfoDTO> findUtilityInformationById(long utility_id) {
		return utilityDaoImpl.findUtilityInformationById(utility_id);
	}
	
	public ResponseEntity<UtilityInfoDTO> findUtilityInformationByType(int utility_type_id) {
		return utilityDaoImpl.findUtilityInformationByType(utility_type_id);
	}
	
	public long findUtilityCount() {
		return utilityDaoImpl.findUtilityCount();
	}
	
	public int addUtilityInformation(@RequestBody Utility utility) {
		return utilityDaoImpl.addUtilityInformation(utility);
	}
	
	public int updateUtilityInformation(long utility_id, Utility utilityDetails) {
		return utilityDaoImpl.updateUtilityInformation(utility_id, utilityDetails);
	}
	
	public int deleteUtilityInformation(long utility_id) {
		return utilityDaoImpl.deleteUtilityInformation(utility_id);
	}
	
	public DeleteAllResponse deleteAllUtilityInformation() {
		DeleteAllResponse response = new DeleteAllResponse();
		int count = utilityDaoImpl.deleteAllUtilityInformation();
		
		response.setCount(count);
		return response;
	}

	public ResponseEntity<UtilityInfoDTO> findUtilityInformationByName(String name) {
		return utilityDaoImpl.findUtilityInformationByName(name);
	}
}
