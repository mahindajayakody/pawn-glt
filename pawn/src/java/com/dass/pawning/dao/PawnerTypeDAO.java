package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.PawnerType;
import com.dass.core.util.QueryCriteria;

public interface PawnerTypeDAO extends AuthorizableDAO{

	public void createPawnerType(UserConfig userconfig,PawnerType pawnerType);
	public void modifyPawnerType(UserConfig userconfig,PawnerType pawnerType);
	public void deletePawnerType(UserConfig userconfig,PawnerType pawnerType);

	public PawnerType getPawnerTypeById(UserConfig userconfig,int recordId);
	public PawnerType getPawnerTypeByCode(UserConfig userconfig,String code);
	public DataBag getAllPawnerType(UserConfig userconfig,List<QueryCriteria> criteriaList );


}
