package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Cartage;

public interface CartageService extends AuthorizableService{
	public void createCartage(UserConfig userConfig,Cartage cartage) throws PawnException;
	public void updateCartage(UserConfig userConfig,Cartage cartage) throws PawnException;
	public void removeCartage(UserConfig userConfig,Cartage cartage) throws PawnException;

	public DataBag getAllCartage(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException;
	public Cartage getCartageById(UserConfig userConfig,int recordId) throws PawnException;
	public Cartage getCartageByCode(UserConfig userConfig,String code) throws PawnException;
	public Cartage getCartageByCodeAndMasterId(UserConfig userConfig, String code,int cartageMasterId) throws PawnException;
}
