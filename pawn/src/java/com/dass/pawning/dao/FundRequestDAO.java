package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.FundRequest;

public interface FundRequestDAO {

	public void createFundRequest(UserConfig userConfig,FundRequest fundRequest);
	public void updateFundRequest(UserConfig userConfig,FundRequest fundRequest);
	public void approveFundRequest(UserConfig userConfig,FundRequest fundRequest);
	
	public FundRequest getFundRequestById(UserConfig userConfig,int recordId);
	public FundRequest getFundRequestByRequestNo(UserConfig userConfig,String requestNo);
	public DataBag getAllFundRequest(UserConfig userConfig,List<QueryCriteria> queryCriteria);
}
