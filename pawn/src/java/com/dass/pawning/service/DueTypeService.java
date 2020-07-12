package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.DueType;

public interface DueTypeService extends AuthorizableService{
	public void createDueType(UserConfig userConfig,DueType dueType)throws PawnException;
	public void updateDueType(UserConfig userConfig,DueType dueType)throws PawnException;
	public void removeDueType(UserConfig userConfig,DueType dueType)throws PawnException;

	public DueType getDueTypeById(UserConfig userConfig,int recordId)throws PawnException;
	public DueType getDueTypeByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllDueType(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
