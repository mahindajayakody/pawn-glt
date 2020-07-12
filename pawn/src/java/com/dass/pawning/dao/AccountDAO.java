package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.GlAccount;

public interface AccountDAO extends AuthorizableDAO{
	public void createAccount(UserConfig userConfig,GlAccount account);
	public void updateAccount(UserConfig userConfig,GlAccount account);
	public void removeAccount(UserConfig userConfig,GlAccount account);

	public DataBag getAllAccount(UserConfig userConfig,List<QueryCriteria> queryCriteria);
	public GlAccount getAccountById(UserConfig userConfig,int recordId);
	public GlAccount getAccountByCode(UserConfig userConfig,String accountCode);

}
