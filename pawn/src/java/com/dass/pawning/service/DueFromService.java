package com.dass.pawning.service;

import java.util.List;
import java.util.Map;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueType;

public interface DueFromService extends AuthorizableService{
	public void createDueFrom(UserConfig userConfig,DueFrom dueFrom)throws PawnException;
	public void updateDueFrom(UserConfig userConfig,DueFrom dueFrom)throws PawnException;
	public void removeDueFrom(UserConfig userConfig,DueFrom dueFrom)throws PawnException;

	public DueFrom getDueFromById(UserConfig userConfig,int recordId)throws PawnException;
	//Trello issue Id 34 fixed By Mahinda
	public List<DueFrom> getDueFromByFacility(UserConfig userConfig,int ticketId)throws PawnException;
	public DataBag getAllDueFrom(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	public DueType getDueTypeByDueCode(UserConfig userConfig, String DueTypeCode)throws PawnException;
	public Map<String, DueType> getReFacilityDueTypes(UserConfig userConfig)throws PawnException;
	public Double getOutstandingForFacility(UserConfig userConfig,String facilityNo) throws PawnException;
	public List<DueFrom> getAllDueFrom(UserConfig userConfig,int ticketId)throws PawnException;

}
