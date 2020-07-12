package com.dass.pawning.service.impl;


import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.SchemeDAO;
import com.dass.pawning.domain.Schemes;
import com.dass.pawning.service.SchemeService;

public class SchemeServiceImpl extends AuthorizableServiceImpl implements SchemeService {

private SchemeDAO schemeDAO;

	public void setSchemeDAO(SchemeDAO schemeDAO) {
		this.schemeDAO = schemeDAO;
	}

	public void createScheme(UserConfig userConfig, Schemes  schemes)throws PawnException {
		schemeDAO.createScheme(userConfig, schemes);
	}


	public void updateScheme(UserConfig userConfig, Schemes  schemes)throws PawnException {
		schemeDAO.updateScheme(userConfig, schemes);
	}

	public DataBag getAllScheme(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return schemeDAO.getAllScheme(userConfig, criteriaList);
	}

	public Schemes getSchemeByCode(UserConfig userConfig, String code)throws PawnException {
		return schemeDAO.getSchemeByCode(userConfig, code);
	}

	public Schemes getSchemeById(UserConfig userConfig, int recordId)throws PawnException {
		return schemeDAO.getSchemeById(userConfig, recordId);
	}

	public Schemes getSchemesByCodeAndProductId(UserConfig userConfig, String code, int productId) throws PawnException {
		return schemeDAO.getSchemesByCodeAndProductId(userConfig, code, productId);
	}
}
