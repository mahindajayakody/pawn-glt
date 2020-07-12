package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.domain.TicketArticle;
import com.dass.pawning.dto.AuthorizeTicketDTO;

public interface TicketDAO {
	public String createTicket(UserConfig userConfig,Ticket ticket,List<TicketArticle> list) throws PawnException;
	public DataBag getAllTicket(UserConfig userConfig,List<QueryCriteria> criteriaList) ;
	public List getSerchTicketData(UserConfig userConfig,String sql);
	public Ticket getTicketById(UserConfig userConfig,int ticketId);
	public Ticket getTicketByTicketNumber(UserConfig userConfig,String number);
	public AuthorizeTicketDTO getAuthorizeTicketData(UserConfig userConfig,int ticketId);
	public void authorizeTicket(UserConfig userConfig, Ticket ticket, boolean authorize);
	public DataBag getAllTicketWithOR(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public List getClientExposure(UserConfig userConfig,int pawnId);
	public void update(UserConfig userSession, Ticket ticket);

}
