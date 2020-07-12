package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.TicketStatus;

public interface TicketStatusService extends AuthorizableService{
	public void createTicketStatus(UserConfig userConfig,TicketStatus ticketStatus)throws PawnException;
	public void updateTicketStatus(UserConfig userConfig,TicketStatus ticketStatus)throws PawnException;
	public void removeTicketStatus(UserConfig userConfig,TicketStatus ticketStatus)throws PawnException;

	public TicketStatus geTicketStatusById(UserConfig userConfig,int recordId)throws PawnException;
	public TicketStatus geTicketStatusByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllTicketStatus(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
}
