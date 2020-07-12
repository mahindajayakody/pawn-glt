package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ParameterValue;

public interface ParameterValueService extends AuthorizableService{
	public void createParameterValue(UserConfig userConfig,ParameterValue parameterValue)throws PawnException;
	public void updateParameterValue(UserConfig userConfig,ParameterValue parameterValue)throws PawnException;
	public void removeParameterValue(UserConfig userConfig,ParameterValue parameterValue)throws PawnException;

	public ParameterValue getParameterValueById(UserConfig userConfig,int recordId)throws PawnException;
	public DataBag getAllParameterValue(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
