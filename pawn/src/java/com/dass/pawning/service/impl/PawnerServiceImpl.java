package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.PawnerDAO;
import com.dass.pawning.domain.Pawner;
import com.dass.pawning.service.PawnerService;

public class PawnerServiceImpl implements PawnerService {
	private PawnerDAO pawnerDAO;
	
	public void setPawnerDAO(PawnerDAO pawnerDAO) {
		this.pawnerDAO = pawnerDAO;
	}
	
	public void createPawner(UserConfig userConfig, Pawner pawner)throws PawnException {
		pawnerDAO.createPawner(userConfig, pawner);
	}

	public void updatePawner(UserConfig userConfig, Pawner pawner)throws PawnException {
		pawnerDAO.updatePawner(userConfig, pawner);
	}
	
	public DataBag getAllPawner(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return pawnerDAO.getAllPawner(userConfig, criteriaList);
	}

	public Pawner getPawnerByCode(UserConfig userConfig, String code)throws PawnException {
		return pawnerDAO.getPawnerByCode(userConfig, code);
	}

	public Pawner getPawnerById(UserConfig userConfig, int recordId)throws PawnException {
		return pawnerDAO.getPawnerById(userConfig, recordId);
	}

	public Pawner getPawnerByIdOrBr(UserConfig userConfig, String idOrBr) throws PawnException {		
		return pawnerDAO.getPawnerByIdOrBr(userConfig, idOrBr);
	}

	public List<Pawner> getAllPawnerByType(UserConfig userConfig, String type) throws PawnException {
		return pawnerDAO.getAllPawnerByType(userConfig, type);
	}

	public List<Pawner> getAllPawnerBySQL(UserConfig userConfig, String sql) throws PawnException {
		return pawnerDAO.getAllPawnerBySQL(userConfig, sql);
	}
}
