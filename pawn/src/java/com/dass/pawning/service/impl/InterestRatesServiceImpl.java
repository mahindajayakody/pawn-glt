package com.dass.pawning.service.impl;


import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.InterestRatesDAO;
import com.dass.pawning.domain.InterestRates;
import com.dass.pawning.domain.InterestSlab;
import com.dass.pawning.service.InterestRatesService;

public class InterestRatesServiceImpl extends AuthorizableServiceImpl implements InterestRatesService {

private InterestRatesDAO interestRatesDAO;

	public void setInterestRatesDAO(InterestRatesDAO interestRatesDAO) {
		this.interestRatesDAO = interestRatesDAO;
	}

	public void createInterestRates(UserConfig userConfig, InterestRates interestRates,List<InterestSlab> slabs)throws PawnException {
		interestRatesDAO.createInterestRates(userConfig, interestRates,slabs);
	}


	public void updateInterestRates(UserConfig userConfig, InterestRates  interestRates,List<InterestSlab> slabs)throws PawnException {
		interestRatesDAO.updateInterestRates(userConfig, interestRates,slabs);
	}

	public DataBag getAllInterestRates(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return interestRatesDAO.getAllInterestRates(userConfig, criteriaList);
	}

	public InterestRates getInterestRateByCode(UserConfig userConfig, String code)throws PawnException {
		return interestRatesDAO.getInterestRateByCode(userConfig, code);
	}

	public InterestRates getInterestRateById(UserConfig userConfig, int recordId)throws PawnException {
		return interestRatesDAO.getInterestRateById(userConfig, recordId);
	}

	public DataBag getAllInterestSlabs(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return interestRatesDAO.getAllInterestSlabs(userConfig, criteriaList);
	}

}
