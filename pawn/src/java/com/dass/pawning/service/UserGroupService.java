package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.UserGroup;


public interface UserGroupService extends AuthorizableService{
	public void createUserGroup(UserConfig userConfig,UserGroup userGroup)throws PawnException;
	public void updateUserGroup(UserConfig userConfig,UserGroup userGroup)throws PawnException;
	public void removeUserGroup(UserConfig userConfig,UserGroup userGroup)throws PawnException;

	public UserGroup getUserGroupById(UserConfig userConfig,int recordId)throws PawnException;
	public UserGroup getUserGroupByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllUserGroup(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
}
