package com.dass.pawning.dao;

import java.util.Date;
import java.util.List;

import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.FundDeposit;

public interface FundDepositDAO {

	public void createFundDeposit(UserConfig userConfig,FundDeposit fundDeposit);
	public void approveFundDeposit(UserConfig userConfig,FundDeposit fundDeposit);
	
	public FundDeposit getFundDepositById(UserConfig userConfig,int recordId);
	public FundDeposit getFundDepositByDepositNo(UserConfig userConfig,String depositNo);
	public DataBag getAllFundDeposit(UserConfig userConfig,List<QueryCriteria> queryCriteria);
	public List<Object[]> getTiketsForTheDay(UserConfig userConfig) ;
	public List<Object[]> getReceiptsForTheDay(UserConfig userConfig) ;
	public List<Object[]> getTiketAllForTheDay(UserConfig userConfig, Date printDate) ;
	public List<Object[]> getReceiptAllForTheDay(UserConfig userConfig, Date printDate);
	public List<Object[]> getFundRequestAllForTheDay(UserConfig userConfig, Date printDate) ;
	public List<Object[]> getFundDepositAllForTheDay(UserConfig userConfig, Date printDate);
}
