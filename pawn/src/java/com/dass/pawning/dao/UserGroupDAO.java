package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.UserGroup;

public interface UserGroupDAO extends AuthorizableDAO{
	public void createUserGroup(UserConfig userConfig,UserGroup userGroup);
	public void updateUserGroup(UserConfig userConfig,UserGroup userGroup);
	public void removeUserGroup(UserConfig userConfig,UserGroup userGroup);

	public UserGroup getUserGroupById(UserConfig userConfig,int recordId);
	public UserGroup getUserGroupByCode(UserConfig userConfig,String code);
	public DataBag getAllUserGroup(UserConfig userConfig,List<QueryCriteria> criteriaList);
}
