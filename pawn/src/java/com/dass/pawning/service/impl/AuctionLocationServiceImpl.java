package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.AuctionLocationDAO;
import com.dass.pawning.domain.AuctionLocation;
import com.dass.pawning.service.AuctionLocationService;

public class AuctionLocationServiceImpl extends AuthorizableServiceImpl implements AuctionLocationService {
	private AuctionLocationDAO auctionLocationDAO;

	public void setAuctionLocationDAO(AuctionLocationDAO auctionLocationDAO){
		this.auctionLocationDAO=auctionLocationDAO;
	}

	public void createAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation) throws PawnException {
		auctionLocationDAO.createAuctionLocation(userConfig, auctionLocation);
	}

	public DataBag getAllAuctionLocation(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return auctionLocationDAO.getAllAuctionLocation(userConfig, criteriaList);
	}

	public AuctionLocation getAuctionLocationByCode(UserConfig userConfig,String code) throws PawnException {
		return auctionLocationDAO.getAuctionLocationByCode(userConfig, code);
	}

	public AuctionLocation getAuctionLocationById(UserConfig userConfig,int recordId) throws PawnException {
		return auctionLocationDAO.getAuctionLocationById(userConfig, recordId);
	}

	public void removeAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation) throws PawnException {
		auctionLocationDAO.removeAuctionLocation(userConfig, auctionLocation);
	}

	public void updateAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation) throws PawnException {
		auctionLocationDAO.updateAuctionLocation(userConfig, auctionLocation);
	}

}
