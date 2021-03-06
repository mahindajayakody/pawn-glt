package com.dass.core.util;

import javax.persistence.MappedSuperclass;

import com.dass.core.bean.Trace;
import com.dass.core.exception.PawnException;

@MappedSuperclass
public abstract class AuthorizableServiceImpl implements AuthorizableService {
	private AuthorizableDAO authorizableDAO;
	
	public void setAuthorizableDAO(AuthorizableDAO authorizableDAO) {
		this.authorizableDAO = authorizableDAO;
	}

	public void authorizeCreation(UserConfig userConfig, Trace trace,boolean authorize) throws PawnException {
		authorizableDAO.authorizeCreation(userConfig, trace, authorize);
	}

	public void authorizeDeletion(UserConfig userConfig, Trace trace,boolean authorize) throws PawnException {
		authorizableDAO.authorizeDeletion(userConfig, trace, authorize);
	}

	public void authorizeUpdation(UserConfig userConfig, Trace trace,boolean authorize) throws PawnException {
		authorizableDAO.authorizeUpdation(userConfig, trace, authorize);
	}
}
