package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.AuctionLocation;

public interface AuctionLocationDAO extends AuthorizableDAO{
	public void createAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation);
	public void updateAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation);
	public void removeAuctionLocation(UserConfig userConfig,AuctionLocation auctionLocation);

	public AuctionLocation getAuctionLocationById(UserConfig userConfig,int recordId);
	public AuctionLocation getAuctionLocationByCode(UserConfig userConfig,String code);
	public DataBag getAllAuctionLocation(UserConfig userConfig,List<QueryCriteria> criteriaList);

}
