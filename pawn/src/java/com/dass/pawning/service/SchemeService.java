package com.dass.pawning.service;

import java.util.List;
import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Schemes;
public interface SchemeService extends AuthorizableService{
	public void createScheme(UserConfig userConfig,Schemes schemes)throws PawnException;
	public void updateScheme(UserConfig userConfig,Schemes schemes)throws PawnException;

	public Schemes getSchemeById(UserConfig userConfig,int recordId)throws PawnException;
	public Schemes getSchemeByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllScheme(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	public Schemes getSchemesByCodeAndProductId(UserConfig userConfig,String code,int productId)throws PawnException;
}
