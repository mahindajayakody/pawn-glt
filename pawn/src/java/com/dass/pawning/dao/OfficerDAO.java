package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Officer;

public interface OfficerDAO extends AuthorizableDAO{
	public void createOfficer(UserConfig userConfig,Officer officer);
	public void updateOfficer(UserConfig userConfig,Officer officer);
	public void removeOfficer(UserConfig userConfig,Officer officer);
	
	public Officer getOfficerById(UserConfig userConfig,int recordId);
	public Officer getOfficerByUserName(UserConfig userConfig,String code);
	public DataBag getAllOfficer(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public Officer getOfficerByPawnerId(UserConfig userConfig,int pawnerId);
	
}
