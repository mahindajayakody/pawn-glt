package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.CartageDAO;
import com.dass.pawning.domain.Cartage;
import com.dass.pawning.service.CartageService;

public class CartageServiceImpl extends AuthorizableServiceImpl implements CartageService {
	private CartageDAO cartageDAO;

	public void setCartageDAO(CartageDAO cartageDAO) {
		this.cartageDAO = cartageDAO;
	}

	public void createCartage(UserConfig userConfig, Cartage cartage)throws PawnException {
		cartageDAO.createCartage(userConfig, cartage);
	}

	public DataBag getAllCartage(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return cartageDAO.getAllCartage(userConfig, criteriaList);
	}

	public Cartage getCartageByCode(UserConfig userConfig, String code)throws PawnException {
		return cartageDAO.getCartageByCode(userConfig, code);
	}

	public Cartage getCartageById(UserConfig userConfig, int recordId)throws PawnException {
		return cartageDAO.getCartageById(userConfig, recordId);
	}

	public void removeCartage(UserConfig userConfig, Cartage cartage)throws PawnException {
		cartageDAO.removeCartage(userConfig, cartage);
	}

	public void updateCartage(UserConfig userConfig, Cartage cartage)throws PawnException {
		cartageDAO.updateCartage(userConfig, cartage);
	}

	public Cartage getCartageByCodeAndMasterId(UserConfig userConfig, String code, int cartageMasterId) throws PawnException {
		return cartageDAO.getCartageByCodeAndMasterId(userConfig, code, cartageMasterId);
	}
}
