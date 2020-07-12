package com.dass.pawning.dao;

import java.util.Collection;
import java.util.List;

import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ProgramAccess;
import com.dass.pawning.dto.ProgramAccessDTO;

public interface ProgramAccessDAO {
	public boolean createOrUpdateOfficerAccess(UserConfig userConfig,List<ProgramAccess> accessList);
	public Collection<ProgramAccessDTO> getAllProgramsWithAccessByGroupId(int userGroupId);
	public UserConfig validateUser(int companyId,String userName,String password,String sessionId);
	public void logOut(UserConfig userConfig);
	public Boolean isActiveUser(int companyId,String userName);
}
