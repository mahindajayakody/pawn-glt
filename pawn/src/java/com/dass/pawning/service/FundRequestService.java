package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.FundRequest;

public interface FundRequestService extends AuthorizableService {
	public void createFundRequest(UserConfig userConfig,FundRequest fundRequest) throws PawnException;
	public void approveFundRequest(UserConfig userConfig,FundRequest fundRequest) throws PawnException;
	public void updateFundRequest(UserConfig userConfig,FundRequest fundRequest) throws PawnException;
	
	public FundRequest getFundRequestById(UserConfig userConfig,int recordId) throws PawnException;
	public FundRequest getFundRequestByRequestNo(UserConfig userConfig,String requestNo) throws PawnException;
	public DataBag getAllFundRequest(UserConfig userConfig, List<QueryCriteria>criteriaList) throws PawnException;
	
}
