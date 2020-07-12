package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueType;

public interface DueFromDAO extends AuthorizableDAO{
	public void createDueFrom(UserConfig userConfig,DueFrom dueFrom);
	public void updateDueFrom(UserConfig userConfig,DueFrom dueFrom);
	public void removeDueFrom(UserConfig userConfig,DueFrom dueFrom);

	public DueFrom getDueFromById(UserConfig userConfig,int recordId);
	//Trello issue Id 34 fixed By Mahinda
	public List<DueFrom> getDueFromByFacility(UserConfig userConfig,int ticketId);
	public DataBag getAllDueFrom(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public DueType getDueTypeByDueCode(UserConfig userConfig, String DueTypeCode);
	public List<DueFrom> getAllDueFrom(UserConfig userConfig,int ticketId);

}
