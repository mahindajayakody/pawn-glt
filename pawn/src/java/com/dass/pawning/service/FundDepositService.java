package com.dass.pawning.service;

import java.util.Date;
import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.FundDeposit;

public interface FundDepositService extends AuthorizableService {
	public void createFundDeposit(UserConfig userConfig,FundDeposit fundDeposit) throws PawnException;
	public void approveFundDeposit(UserConfig userConfig,FundDeposit fundDeposit) throws PawnException;
	
	public FundDeposit getFundDepositById(UserConfig userConfig,int recordId) throws PawnException;
	public FundDeposit getFundDepositByDepositNo(UserConfig userConfig,String depositNo) throws PawnException;
	public DataBag getAllFundDeposit(UserConfig userConfig, List<QueryCriteria>criteriaList) throws PawnException;
	public List<Object[]> getTiketsForTheDay(UserConfig userConfig) throws PawnException;
	public List<Object[]> getReceiptsForTheDay(UserConfig userConfig) throws PawnException;
	public List<Object[]> getTiketAllForTheDay(UserConfig userConfig, Date printDate)throws PawnException;
	public List<Object[]> getReceiptAllForTheDay(UserConfig userConfig, Date printDate)throws PawnException;
	public List<Object[]> getFundRequestAllForTheDay(UserConfig userConfig, Date printDate) throws PawnException;
	public List<Object[]> getFundDepositAllForTheDay(UserConfig userConfig, Date printDate)throws PawnException;
}
