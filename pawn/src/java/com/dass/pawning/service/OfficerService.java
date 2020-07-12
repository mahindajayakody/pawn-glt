package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Officer;

public interface OfficerService extends AuthorizableService{
	public void createOfficer(UserConfig userConfig,Officer officer)throws PawnException;
	public void updateOfficer(UserConfig userConfig,Officer officer)throws PawnException;
	public void removeOfficer(UserConfig userConfig,Officer officer)throws PawnException;

	public Officer getOfficerById(UserConfig userConfig,int recordId)throws PawnException;
	public Officer getOfficerByUserName(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllOfficer(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	public Officer getOfficerByPawnerId(UserConfig userConfig,int pawnerId)throws PawnException;
}
