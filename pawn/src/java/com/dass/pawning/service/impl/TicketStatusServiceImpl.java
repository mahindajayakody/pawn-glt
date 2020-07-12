package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.TicketStatusDAO;
import com.dass.pawning.domain.TicketStatus;
import com.dass.pawning.service.TicketStatusService;

public class TicketStatusServiceImpl extends AuthorizableServiceImpl implements TicketStatusService {
	private TicketStatusDAO ticketStatusDAO;

	public void setTicketStatusDAO(TicketStatusDAO ticketStatusDAO) {
		this.ticketStatusDAO = ticketStatusDAO;
	}

	public void createTicketStatus(UserConfig userConfig, TicketStatus ticketStatus) throws PawnException {
		ticketStatusDAO.createTicketStatus(userConfig, ticketStatus);
	}

	public void removeTicketStatus(UserConfig userConfig,TicketStatus ticketStatus) throws PawnException {
		ticketStatusDAO.removeTicketStatus(userConfig, ticketStatus);
	}

	public void updateTicketStatus(UserConfig userConfig,TicketStatus ticketStatus) throws PawnException {
		ticketStatusDAO.updateTicketStatus(userConfig, ticketStatus);
	}

	public TicketStatus geTicketStatusByCode(UserConfig userConfig, String code) throws PawnException {
		return ticketStatusDAO.geTicketStatusByCode(userConfig, code);
	}

	public TicketStatus geTicketStatusById(UserConfig userConfig, int recordId) throws PawnException {
		return ticketStatusDAO.geTicketStatusById(userConfig, recordId);
	}

	public DataBag getAllTicketStatus(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return ticketStatusDAO.getAllTicketStatus(userConfig, criteriaList);
	}

}
