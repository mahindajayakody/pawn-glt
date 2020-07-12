package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.AuctionDAO;
import com.dass.pawning.domain.AuctionMaster;
import com.dass.pawning.service.AuctionService;

public class AuctionServiceImpl extends AuthorizableServiceImpl implements AuctionService {
	private AuctionDAO auctionDAO;
	public void setAuctionDAO(AuctionDAO auctionDAO) {
		this.auctionDAO = auctionDAO;
	}
	
	public void createAuction(UserConfig userConfig, AuctionMaster auctionMaster) throws PawnException {
		auctionDAO.createAuction(userConfig, auctionMaster);
	}

	public DataBag getAllAuction(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return auctionDAO.getAllAuction(userConfig, criteriaList);
	}

	public AuctionMaster getAuctionByCode(UserConfig userConfig, String code)throws PawnException {
		return auctionDAO.getAuctionByCode(userConfig, code);
	}

	public AuctionMaster getAuctionById(UserConfig userConfig, int recordId)throws PawnException {
		return auctionDAO.getAuctionById(userConfig, recordId);
	}

	public void removeAuction(UserConfig userConfig, AuctionMaster auctionMaster)throws PawnException {
		auctionDAO.removeAuction(userConfig, auctionMaster);
	}

	public void updateAuction(UserConfig userConfig, AuctionMaster auctionMaster)throws PawnException {
		auctionDAO.updateAuction(userConfig, auctionMaster);
	}
	
	public DataBag getAllActiveAuction(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException {
		return auctionDAO.getAllActiveAuction(userConfig, criteriaList);
	}
	
		
	public List getAllLapsTickets(UserConfig userConfig,String sql)throws PawnException {
		return auctionDAO.getAllLapsTickets(userConfig, sql);
	}
	
	public void createAuctionMark(UserConfig userConfig, int branchId,int productId,int auctionId ,Integer[] tikets,Double upsetValue)throws PawnException {
		 auctionDAO.createAuctionMark(userConfig, branchId, productId, auctionId, tikets,upsetValue);
	}

	public List<AuctionMaster> getAllCommonAuction(UserConfig userConfig, int isCommon) {
		return auctionDAO.getAllCommonAuction(userConfig, isCommon);
	}
}
