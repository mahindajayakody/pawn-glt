package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.CartageMarster;

public interface CartageMarsterDAO extends AuthorizableDAO{
	public void createCartageMarster(UserConfig userConfig,CartageMarster cartageMarster);
	public void updateCartageMarster(UserConfig userConfig,CartageMarster cartageMarster);
	public void removeCartageMarster(UserConfig userConfig,CartageMarster cartageMarster);

	public CartageMarster getCartageMarsterById(UserConfig userConfig,int recordId);
	public CartageMarster getCartageMarsterByCode(UserConfig userConfig,String code);
	public DataBag getAllCartageMarster(UserConfig userConfig,List<QueryCriteria> criteriaList);

}
