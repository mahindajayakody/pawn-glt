package com.dass.pawning.dao;


import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Schemes;


public interface SchemeDAO extends AuthorizableDAO{
	public void createScheme(UserConfig userConfig,Schemes schemes);
	public void updateScheme(UserConfig userConfig,Schemes schemes);

	public Schemes getSchemeById(UserConfig userConfig,int recordId);
	public Schemes getSchemeByCode(UserConfig userConfig,String code);
	public DataBag getAllScheme(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public Schemes getSchemesByCodeAndProductId(UserConfig userConfig,String code,int productId);
}
