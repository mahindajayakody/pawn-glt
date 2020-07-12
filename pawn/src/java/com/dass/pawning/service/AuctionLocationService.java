package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.AuctionLocation;


public interface AuctionLocationService extends AuthorizableService{
	public void createAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation)throws PawnException;
	public void updateAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation)throws PawnException;
	public void removeAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation)throws PawnException;

	public AuctionLocation getAuctionLocationById(UserConfig userConfig,int recordId)throws PawnException;
	public AuctionLocation getAuctionLocationByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllAuctionLocation(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
