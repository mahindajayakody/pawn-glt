package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ClosureType;

public interface ClosureTypeDAO extends AuthorizableDAO{
	public void createClosureType(UserConfig userConfig,ClosureType closureType);
	public void updateClosureType(UserConfig userConfig,ClosureType closureType);
	public void removeClosureType(UserConfig userConfig,ClosureType closureType);

	public ClosureType getClosureTypeById(UserConfig userConfig,int recordId);
	public ClosureType getClosureTypeByCode(UserConfig userConfig,String code);
	public DataBag getAllClosureType(UserConfig userConfig,List<QueryCriteria> criteriaList);
}
