package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.TicketStatus;

public interface TicketStatusDAO extends AuthorizableDAO{
	public void createTicketStatus(UserConfig userConfig,TicketStatus ticketStatus);
	public void updateTicketStatus(UserConfig userConfig,TicketStatus ticketStatus);
	public void removeTicketStatus(UserConfig userConfig,TicketStatus ticketStatus);

	public TicketStatus geTicketStatusById(UserConfig userConfig,int recordId);
	public TicketStatus geTicketStatusByCode(UserConfig userConfig,String code);
	public DataBag getAllTicketStatus(UserConfig userConfig,List<QueryCriteria> criteriaList);
}
