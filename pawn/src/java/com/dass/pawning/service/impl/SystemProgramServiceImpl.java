package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.QueryCriteria;
import com.dass.pawning.dao.SystemProgramDAO;
import com.dass.pawning.domain.SystemProgram;
import com.dass.pawning.service.SystemProgramService;

public class SystemProgramServiceImpl implements SystemProgramService {
	private SystemProgramDAO systemProgramDAO;

	public void setSystemProgramDAO(SystemProgramDAO systemProgramDAO) {
		this.systemProgramDAO = systemProgramDAO;
	}

	public List<SystemProgram> getAllSystemPrograms(List<QueryCriteria> criteriaList) throws PawnException {		
		return systemProgramDAO.getAllSystemPrograms(criteriaList);
	}
}
