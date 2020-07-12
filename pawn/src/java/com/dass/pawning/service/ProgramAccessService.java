package com.dass.pawning.service;


import java.util.Collection;
import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ProgramAccess;
import com.dass.pawning.dto.ProgramAccessDTO;

public interface ProgramAccessService {
	public boolean createOrUpdateOfficerAccess(UserConfig userConfig,List<ProgramAccess> accessList)throws PawnException;
	public Collection<ProgramAccessDTO> getAllProgramsWithAccessByGroupId(int userGroupId)throws PawnException;
	public UserConfig validateUser(int companyId,String userName,String password, String sessionId)throws PawnException;
	public void logOut(UserConfig userConfig)throws PawnException;
}
