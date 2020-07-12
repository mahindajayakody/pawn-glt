package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.AuctionMaster;

public interface AuctionDAO extends AuthorizableDAO {
	public void createAuction(UserConfig userConfig,AuctionMaster auctionMaster);
	public void updateAuction(UserConfig userConfig,AuctionMaster auctionMaster);
	public void removeAuction(UserConfig userConfig,AuctionMaster auctionMaster);
	public AuctionMaster getAuctionById(UserConfig userConfig,int recordId);
	public AuctionMaster getAuctionByCode(UserConfig userConfig,String code);
	public DataBag getAllAuction(UserConfig userConfig,List<QueryCriteria>criteriaList);
	public DataBag getAllActiveAuction(UserConfig userConfig,List<QueryCriteria> criteriaList) ;
	public List getAllLapsTickets(UserConfig userConfig,String sql);
	public void createAuctionMark(UserConfig userConfig, int branchId,int productId,int auctionId ,Integer[] tikets, Double upsetValue) ;
	public List<AuctionMaster> getAllCommonAuction(UserConfig userConfig,int isCommon);
}
