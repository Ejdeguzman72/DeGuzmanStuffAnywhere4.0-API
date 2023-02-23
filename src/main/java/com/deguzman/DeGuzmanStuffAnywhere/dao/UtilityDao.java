package com.deguzman.DeGuzmanStuffAnywhere.dao;

import java.util.List;

import com.deguzman.DeGuzmanStuffAnywhere.dto.UtilityInfoDTO;
import com.deguzman.DeGuzmanStuffAnywhere.model.Utility;

public interface UtilityDao {

	public List<UtilityInfoDTO> findAllUtilityInformation();

	public List<UtilityInfoDTO> findUtilityInformationByDueDate(String dueDate);

	public UtilityInfoDTO findUtilityInformationById(long utility_id);

	public UtilityInfoDTO findUtilityInformationByName(String name);

	public UtilityInfoDTO findUtilityInformationByType(int utility_type_id);

	public long findUtilityCount();

	public int addUtilityInformation(Utility utility);

	public int updateUtilityInformation(long utility_id, Utility utilityDetails);

	public int deleteUtilityInformation(long utility_id);

	public int deleteAllUtilityInformation();

}
