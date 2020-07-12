package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ClosureType;

public interface ClosureTypeService extends AuthorizableService{
	public void createClosureType(UserConfig userConfig,ClosureType closureType)throws PawnException;
	public void updateClosureType(UserConfig userConfig,ClosureType closureType)throws PawnException;
	public void removeClosureType(UserConfig userConfig,ClosureType closureType)throws PawnException;

	public ClosureType getClosureTypeById(UserConfig userConfig,int recordId)throws PawnException;
	public ClosureType getClosureTypeByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllClosureType(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
