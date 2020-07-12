package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.CartageMarster;

public interface CartageMarsterService extends AuthorizableService{
	public void createCartageMarster(UserConfig userConfig,CartageMarster cartageMarster)throws PawnException;
	public void updateCartageMarster(UserConfig userConfig,CartageMarster cartageMarster)throws PawnException;
	public void removeCartageMarster(UserConfig userConfig,CartageMarster cartageMarster)throws PawnException;

	public CartageMarster getCartageMarsterById(UserConfig userConfig,int recordId)throws PawnException;
	public CartageMarster getCartageMarsterByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllCartageMarster(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;

}
