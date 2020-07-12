package com.dass.pawning.service;

import java.util.List;
import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.InterestRates;
import com.dass.pawning.domain.InterestSlab;
public interface InterestRatesService extends AuthorizableService{
	public void createInterestRates(UserConfig userConfig, InterestRates interestRates,List<InterestSlab> slabs)throws PawnException;
	public void updateInterestRates(UserConfig userConfig,InterestRates interestRates,List<InterestSlab> slabs)throws PawnException;

	public InterestRates getInterestRateById(UserConfig userConfig,int recordId)throws PawnException;
	public InterestRates getInterestRateByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllInterestRates(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	public DataBag getAllInterestSlabs(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException;
}
