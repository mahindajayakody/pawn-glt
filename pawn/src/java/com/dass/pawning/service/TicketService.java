package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.domain.TicketArticle;
import com.dass.pawning.dto.AuthorizeTicketDTO;

public interface TicketService{
	public String createTicket(UserConfig userConfig,Ticket ticket,List<TicketArticle> list)throws PawnException;
	public DataBag getAllTicket(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException;
	public List getSerchTicketData(UserConfig userConfig,String sql)throws PawnException;
	public Ticket getTicketById(UserConfig userConfig,int ticketId)throws PawnException;
	public Ticket getTicketByTicketNumber(UserConfig userConfig,String number)throws PawnException;
	public AuthorizeTicketDTO getAuthorizeTicketData(UserConfig userConfig,int ticketId)throws PawnException;
	public void authorizeTicket(UserConfig userConfig, Ticket ticket,boolean authorize)throws PawnException;
	public DataBag getAllTicketWithOR(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	public List getClientExposure(UserConfig userConfig,int pawnerId) throws PawnException;
	public void update(UserConfig userSession, Ticket ticket) throws PawnException;

}
