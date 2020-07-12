package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.TicketDAO;
import com.dass.pawning.domain.Ticket;
import com.dass.pawning.domain.TicketArticle;
import com.dass.pawning.dto.AuthorizeTicketDTO;
import com.dass.pawning.service.TicketService;

public class TicketServiceImpl implements TicketService {
	private TicketDAO ticketDAO;
	
	public void setTicketDAO(TicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}

	public String createTicket(UserConfig userConfig, Ticket ticket, List<TicketArticle> list) throws PawnException{
		return ticketDAO.createTicket(userConfig, ticket, list);
	}

	public DataBag getAllTicket(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return ticketDAO.getAllTicket(userConfig, criteriaList);
	}

	public List getSerchTicketData(UserConfig userConfig,String sql) throws PawnException {
		return ticketDAO.getSerchTicketData(userConfig,sql);
	}

	public Ticket getTicketById(UserConfig userConfig, int ticketId) throws PawnException {
		return ticketDAO.getTicketById(userConfig, ticketId);
	}

	public Ticket getTicketByTicketNumber(UserConfig userConfig, String number) throws PawnException {
		return ticketDAO.getTicketByTicketNumber(userConfig, number);
	}

	public void authorizeTicket(UserConfig userConfig, Ticket ticket , boolean authorize) throws PawnException {
		ticketDAO.authorizeTicket(userConfig, ticket , authorize);
	}

	public AuthorizeTicketDTO getAuthorizeTicketData(UserConfig userConfig, int ticketId) throws PawnException {
		return ticketDAO.getAuthorizeTicketData(userConfig, ticketId);
	}

	public DataBag getAllTicketWithOR(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return ticketDAO.getAllTicketWithOR(userConfig, criteriaList);
	}

	public List getClientExposure(UserConfig userConfig, int pawnId) throws PawnException {
		return ticketDAO.getClientExposure(userConfig, pawnId);
	}

	@Override
	public void update(UserConfig userSession, Ticket ticket)
			throws PawnException {
		ticketDAO.update(userSession,ticket);
		
	}
}
