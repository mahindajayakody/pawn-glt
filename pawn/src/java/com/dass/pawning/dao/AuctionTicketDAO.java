package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.AuctionTicket;

public interface AuctionTicketDAO {
	public AuctionTicket getAuctionTicketByAuctionId(UserConfig userConfig, int auctionId) throws PawnException;
	public AuctionTicket getAuctionTicketById(UserConfig userConfig, int auctionTicketId) throws PawnException;
	public DataBag getAllAuctionTicket(UserConfig userConfig, List<QueryCriteria> criteriList) throws PawnException;
	public DataBag getAllAuctionMarkTicket(UserConfig userConfig, List<QueryCriteria> criteriList) throws PawnException;
	public void allocate(UserConfig userConfig,Integer[] tickets,List<QueryCriteria>criteriaList,int auctionId) throws PawnException;
}
