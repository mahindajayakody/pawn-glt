package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.AuctionExpences;

public interface AuctionExpencesService extends AuthorizableService {
	public void createAuctionExpences(UserConfig userConfig,AuctionExpences auctionExpences) throws PawnException;
	public void updateAuctionExpences(UserConfig userConfig,AuctionExpences auctionExpences) throws PawnException;
	public void removeAuctionExpences(UserConfig userConfig,AuctionExpences auctionExpences) throws PawnException;

	public DataBag getAllAuctionExpences(UserConfig userConfig,List<QueryCriteria> queryCriteria) throws PawnException;
	public AuctionExpences getAuctionExpencesById(UserConfig userConfig,int recordId) throws PawnException;
	public AuctionExpences getAuctionExpencesByCode(UserConfig userConfig,String auctionCode) throws PawnException;
	public AuctionExpences getAuctionExpencesByAuctionId (UserConfig userConfig,int auctionId) throws PawnException;

}
