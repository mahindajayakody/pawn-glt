package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ParameterDAO;
import com.dass.pawning.domain.Parameter;
import com.dass.pawning.service.ParameterService;

public class ParameterServiceImpl extends AuthorizableServiceImpl implements ParameterService {

	private ParameterDAO parameterDAO;
	public void setParameterDAO(ParameterDAO parameterDAO){
		this.parameterDAO=parameterDAO;
	}
	public void createParameter(UserConfig userConfig, Parameter parameter)throws PawnException {
		parameterDAO.createParameter(userConfig, parameter);

	}

	public DataBag getAllParameter(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return parameterDAO.getAllParameter(userConfig, criteriaList);
	}

	public Parameter getParameterByCode(UserConfig userConfig, String code)throws PawnException {
		return parameterDAO.getParameterByCode(userConfig, code);
	}

	public Parameter getParameterById(UserConfig userConfig, int recordId)throws PawnException {
		return parameterDAO.getParameterById(userConfig, recordId);
	}

	public void removeParameter(UserConfig userConfig, Parameter parameter)throws PawnException {
		parameterDAO.removeParameter(userConfig, parameter);

	}

	public void updateParameter(UserConfig userConfig, Parameter parameter)throws PawnException {
		parameterDAO.updateParameter(userConfig, parameter);

	}

}
