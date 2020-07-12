package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.AuctionTicketDAO;
import com.dass.pawning.dao.DueFromDAO;
import com.dass.pawning.dao.DueTypeDAO;
import com.dass.pawning.domain.AuctionTicket;
import com.dass.pawning.service.AuctionTicketService;

public class AuctionTicketServiceImpl implements AuctionTicketService {
	private AuctionTicketDAO auctionTicketDAO;
	private DueFromDAO dueFromDAO;
	private DueTypeDAO dueTypeDAO;
	
	
	/**
	 * @param dueTypeDAO the dueTypeDAO to set
	 */
	public void setDueTypeDAO(DueTypeDAO dueTypeDAO) {
		this.dueTypeDAO = dueTypeDAO;
	}

	/**
	 * @param dueFromDAO the dueFromDAO to set
	 */
	public void setDueFromDAO(DueFromDAO dueFromDAO) {
		this.dueFromDAO = dueFromDAO;
	}

	public void setAuctionTicketDAO(AuctionTicketDAO auctionTicketDAO) {
		this.auctionTicketDAO = auctionTicketDAO;
	}

	public DataBag getAllAuctionTicket(UserConfig userConfig,
			List<QueryCriteria> criteriList) throws PawnException {
		return auctionTicketDAO.getAllAuctionTicket(userConfig, criteriList);
	}


	public AuctionTicket getAuctionTicketByAuctionId(UserConfig userConfig,
			int auctionId) throws PawnException {
		return auctionTicketDAO.getAuctionTicketByAuctionId(userConfig, auctionId);
	}


	public AuctionTicket getAuctionTicketById(UserConfig userConfig,
			int auctionTicketId) throws PawnException {
		return auctionTicketDAO.getAuctionTicketById(userConfig, auctionTicketId);
	}

	public void allocate(UserConfig userConfig, Integer[] tickets,List<QueryCriteria>criteriaList,int auctionId)
			throws PawnException {
		auctionTicketDAO.allocate(userConfig, tickets,criteriaList,auctionId);		
	}

	public DataBag getAllAuctionMarkTicket(UserConfig userConfig,
			List<QueryCriteria> criteriList) throws PawnException {
		return auctionTicketDAO.getAllAuctionMarkTicket(userConfig, criteriList);
	}
	

}
