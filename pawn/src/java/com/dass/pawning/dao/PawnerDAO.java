package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Pawner;

public interface PawnerDAO{
	public void createPawner(UserConfig userConfig,Pawner pawner);
	public void updatePawner(UserConfig userConfig,Pawner pawner);

	public Pawner getPawnerById(UserConfig userConfig,int recordId);
	public Pawner getPawnerByCode(UserConfig userConfig,String code);
	public Pawner getPawnerByIdOrBr(UserConfig userConfig, String idOrBr) ;
	public List<Pawner> getAllPawnerByType(UserConfig userConfig, String type);
	public List<Pawner> getAllPawnerBySQL(UserConfig userConfig, String sql);
	public DataBag getAllPawner(UserConfig userConfig,List<QueryCriteria> criteriaList);
}
