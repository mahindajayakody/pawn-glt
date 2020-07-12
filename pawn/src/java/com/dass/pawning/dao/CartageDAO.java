package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Cartage;

public interface CartageDAO extends AuthorizableDAO{
	public void createCartage(UserConfig userConfig,Cartage cartage);
	public void updateCartage(UserConfig userConfig,Cartage cartage);
	public void removeCartage(UserConfig userConfig,Cartage cartage);

	public Cartage getCartageById(UserConfig userConfig,int recordId);
	public Cartage getCartageByCode(UserConfig userConfig,String code);
	public DataBag getAllCartage(UserConfig userConfig,List<QueryCriteria> queryCriteria );
	public Cartage getCartageByCodeAndMasterId(UserConfig userConfig, String code,int cartageMasterId) ;
}
