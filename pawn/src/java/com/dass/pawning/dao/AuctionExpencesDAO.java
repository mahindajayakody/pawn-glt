package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.AuctionExpences;


public interface AuctionExpencesDAO extends AuthorizableDAO {
	public void createAuctionExpences(UserConfig userConfig,AuctionExpences auctionExpences);
	public void updateAuctionExpences(UserConfig userConfig,AuctionExpences auctionExpences);
	public void removeAuctionExpences(UserConfig userConfig,AuctionExpences auctionExpences);

	public DataBag getAllAuctionExpences(UserConfig userConfig,List<QueryCriteria> queryCriteria);
	public AuctionExpences getAuctionExpencesById(UserConfig userConfig,int recordId);
	public AuctionExpences getAuctionExpencesByCode(UserConfig userConfig,String auctionCode);
	public AuctionExpences getAuctionExpencesByAuctionId(UserConfig userConfig,int auctionId);

}
