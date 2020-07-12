package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Parameter;

public interface ParameterService extends AuthorizableService{
	public void createParameter(UserConfig userConfig,Parameter parameter)throws PawnException;
	public void updateParameter(UserConfig userConfig,Parameter parameter)throws PawnException;
	public void removeParameter(UserConfig userConfig,Parameter parameter)throws PawnException;

	public Parameter getParameterById(UserConfig userConfig,int recordId)throws PawnException;
	public Parameter getParameterByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllParameter(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
