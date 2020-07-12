package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.DueType;

public interface DueTypeDAO extends AuthorizableDAO{
	public void createDueType(UserConfig userConfig,DueType dueType);
	public void updateDueType(UserConfig userConfig,DueType dueType);
	public void removeDueType(UserConfig userConfig,DueType dueType);

	public DueType getDueTypeById(UserConfig userConfig,int recordId);
	public DueType getDueTypeByCode(UserConfig userConfig,String code);
	public DataBag getAllDueType(UserConfig userConfig,List<QueryCriteria> criteriaList);

}
