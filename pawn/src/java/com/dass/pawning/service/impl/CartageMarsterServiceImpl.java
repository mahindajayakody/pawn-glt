package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.CartageMarsterDAO;
import com.dass.pawning.domain.CartageMarster;
import com.dass.pawning.service.CartageMarsterService;

public class CartageMarsterServiceImpl extends AuthorizableServiceImpl implements CartageMarsterService {
	private CartageMarsterDAO cartageMarsterDAO;

	public void setCartageMarsterDAO(CartageMarsterDAO cartageMarsterDAO) {
		this.cartageMarsterDAO = cartageMarsterDAO;
	}

	public void createCartageMarster(UserConfig userConfig, CartageMarster cartageMarster) throws PawnException {
		cartageMarsterDAO.createCartageMarster(userConfig, cartageMarster);
	}

	public void removeCartageMarster(UserConfig userConfig, CartageMarster cartageMarster) throws PawnException {
		cartageMarsterDAO.removeCartageMarster(userConfig, cartageMarster);
	}

	public void updateCartageMarster(UserConfig userConfig, CartageMarster cartageMarster) throws PawnException {
		cartageMarsterDAO.updateCartageMarster(userConfig, cartageMarster);
	}

	public DataBag getAllCartageMarster(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return cartageMarsterDAO.getAllCartageMarster(userConfig, criteriaList);
	}

	public CartageMarster getCartageMarsterByCode(UserConfig userConfig, String code) throws PawnException {
		return cartageMarsterDAO.getCartageMarsterByCode(userConfig, code);
	}

	public CartageMarster getCartageMarsterById(UserConfig userConfig, int recordId) throws PawnException {
		return cartageMarsterDAO.getCartageMarsterById(userConfig, recordId);
	}
}
