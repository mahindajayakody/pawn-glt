package com.dass.pawning.dao;


import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.InterestRates;
import com.dass.pawning.domain.InterestSlab;


public interface InterestRatesDAO extends AuthorizableDAO{
	public void createInterestRates(UserConfig userConfig, InterestRates interestRates,List<InterestSlab> slabs);
	public void updateInterestRates(UserConfig userConfig,InterestRates interestRates,List<InterestSlab> slabs);

	public InterestRates getInterestRateById(UserConfig userConfig,int recordId);
	public InterestRates getInterestRateByCode(UserConfig userConfig,String code);
	public DataBag getAllInterestRates(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public DataBag getAllInterestSlabs(UserConfig userConfig,List<QueryCriteria> criteriaList) ;

}
