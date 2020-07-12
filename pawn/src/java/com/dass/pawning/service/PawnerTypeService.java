package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.PawnerType;

public interface PawnerTypeService extends AuthorizableService{
	public void createPawnerType(UserConfig userconfig,PawnerType pawnerType)throws PawnException;
	public void modifyPawnerType(UserConfig userconfig,PawnerType pawnerType)throws PawnException;
	public void deletePawnerType(UserConfig userconfig,PawnerType pawnerType)throws PawnException;

	public PawnerType getPawnerTypeById(UserConfig userconfig,int recordId)throws PawnException;
	public PawnerType getPawnerTypeByCode(UserConfig userconfig,String code)throws PawnException;
	public DataBag getAllPawnerType(UserConfig userconfig,List<QueryCriteria> criteriaList )throws PawnException;
}
