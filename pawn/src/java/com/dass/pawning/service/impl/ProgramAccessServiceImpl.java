package com.dass.pawning.service.impl;

import java.util.Collection;
import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ProgramAccessDAO;
import com.dass.pawning.domain.ProgramAccess;
import com.dass.pawning.dto.ProgramAccessDTO;
import com.dass.pawning.service.ProgramAccessService;

public class ProgramAccessServiceImpl implements ProgramAccessService {
	private ProgramAccessDAO programAccessDAO;
	
	public void setProgramAccessDAO(ProgramAccessDAO programAccessDAO) {
		this.programAccessDAO = programAccessDAO;
	}
	
	public boolean createOrUpdateOfficerAccess(UserConfig userConfig,List<ProgramAccess> accessList) throws PawnException {
		return programAccessDAO.createOrUpdateOfficerAccess(userConfig,accessList);
	}

	public Collection<ProgramAccessDTO> getAllProgramsWithAccessByGroupId(int userGroupId) throws PawnException {		
		return programAccessDAO.getAllProgramsWithAccessByGroupId(userGroupId);
	}

	public UserConfig validateUser(int companyId, String userName, String password,String sessionId) throws PawnException {
		return programAccessDAO.validateUser(companyId, userName, password,sessionId);
	}

	public void logOut(UserConfig userConfig) throws PawnException {
		this.programAccessDAO.logOut(userConfig);		
	}
}
