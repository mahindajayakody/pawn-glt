package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.GlAccount;

public interface AccountService extends AuthorizableService{
	public void createAccount(UserConfig userConfig,GlAccount account)throws PawnException;
	public void updateAccount(UserConfig userConfig,GlAccount account)throws PawnException;
	public void removeAccount(UserConfig userConfig,GlAccount account)throws PawnException;
	
	public GlAccount getAccountById(UserConfig userConfig,int recordId)throws PawnException;
	public GlAccount getAccountByCode(UserConfig userConfig,String accountCode)throws PawnException;
	public DataBag getAllAccount(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;


}
