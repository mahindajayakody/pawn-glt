package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Parameter;

public interface ParameterDAO extends AuthorizableDAO{
	public void createParameter(UserConfig userConfig,Parameter parameter);
	public void updateParameter(UserConfig userConfig,Parameter parameter);
	public void removeParameter(UserConfig userConfig,Parameter parameter);

	public Parameter getParameterById(UserConfig userConfig,int recordId);
	public Parameter getParameterByCode(UserConfig userConfig,String code);
	public DataBag getAllParameter(UserConfig userConfig,List<QueryCriteria> criteriaList);

}
