package com.dass.pawning.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.DueFromDAO;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueType;
import com.dass.pawning.service.DueFromService;

public class DueFromServiceImpl extends AuthorizableServiceImpl implements DueFromService {
	private DueFromDAO dueFromDAO;

	public void setDueFromDAO(DueFromDAO dueFromDAO) {
		this.dueFromDAO = dueFromDAO;
	}

	public void createDueFrom(UserConfig userConfig, DueFrom dueFrom) throws PawnException {
		
		dueFromDAO.createDueFrom(userConfig, dueFrom);
	}

	public void removeDueFrom(UserConfig userConfig, DueFrom dueFrom)throws PawnException {
		dueFromDAO.removeDueFrom(userConfig, dueFrom);
	}

	public void updateDueFrom(UserConfig userConfig, DueFrom dueFrom)throws PawnException {
		if (dueFrom.getDueTypeId() == 1 || dueFrom.getDueTypeId() == 2 )
			throw new PawnException("errors.upsystemrecord");
		dueFromDAO.updateDueFrom(userConfig, dueFrom);
	}

	public DataBag getAllDueFrom(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return dueFromDAO.getAllDueFrom(userConfig, criteriaList);
	}
	//Trello issue Id 34 fixed By Mahinda
	public List<DueFrom> getDueFromByFacility(UserConfig userConfig, int ticketId)throws PawnException {
		return dueFromDAO.getDueFromByFacility(userConfig, ticketId);
	}

	public DueFrom getDueFromById(UserConfig userConfig, int recordId)throws PawnException {
		return dueFromDAO.getDueFromById(userConfig, recordId);
	}
	
	public DueType getDueTypeByDueCode(UserConfig userConfig, String DueTypeCode)throws PawnException{
		return dueFromDAO.getDueTypeByDueCode(userConfig, DueTypeCode);
	}

	@Override
	public Map<String, DueType> getReFacilityDueTypes(UserConfig userConfig)
			throws PawnException {
		Map<String,DueType> duetypes = new HashMap<String, DueType>();
		DueType dueType = null;
		//INP due type
		dueType = getDueTypeByDueCode(userConfig, "INP");
		duetypes.put(dueType.getDueType(), dueType);
		//REA due type
		dueType = getDueTypeByDueCode(userConfig, "REA");
		duetypes.put(dueType.getDueType(), dueType);
		//BLA due type
		dueType = getDueTypeByDueCode(userConfig, "BLA");
		duetypes.put(dueType.getDueType(), dueType);
		return duetypes;
	}
	//56. Write the Total Outstanding amount when  SoldNote Confirm
	public Double getOutstandingForFacility(UserConfig userConfig,String facilityNo) throws PawnException{
//		List<DueFrom> dueFroms = dueFromDAO.getDueFromByFacility(userConfig,facilityNo);
		Double totOutStanding =0.00;
//		for (DueFrom dueFrom : dueFroms) {
//			totOutStanding =+ dueFrom.getBalanceAmount();
//		}
		return totOutStanding;
	}

	@Override
	public List<DueFrom> getAllDueFrom(UserConfig userConfig, int ticketId)
			throws PawnException {
				
		return dueFromDAO.getAllDueFrom(userConfig, ticketId);
	}
	
}
