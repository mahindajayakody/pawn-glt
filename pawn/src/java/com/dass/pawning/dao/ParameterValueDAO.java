package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ParameterValue;

public interface ParameterValueDAO extends AuthorizableDAO{
	public void createParameterValue(UserConfig userConfig,ParameterValue parameterValue);
	public void updateParameterValue(UserConfig userConfig,ParameterValue parameterValue);
	public void removeParameterValue(UserConfig userConfig,ParameterValue parameterValue);
	public ParameterValue getParameterValueById(UserConfig userConfig,int recordId);
	//public ParameterValue getParameterValueByCode(UserConfig userConfig,String code);
	public DataBag getAllParameterValue(UserConfig userConfig,List<QueryCriteria >criteriaList);



}
