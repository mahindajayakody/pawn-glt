package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.OfficerDAO;
import com.dass.pawning.domain.Officer;
import com.dass.pawning.service.OfficerService;

public class OfficerServiceImpl extends AuthorizableServiceImpl implements OfficerService {
	private OfficerDAO officerDAO;

	public void setOfficerDAO(OfficerDAO officerDAO) {
		this.officerDAO = officerDAO;
	}

	public void createOfficer(UserConfig userConfig, Officer officer)throws PawnException {
		officerDAO.createOfficer(userConfig, officer);
	}

	public void removeOfficer(UserConfig userConfig, Officer officer)throws PawnException {
		officerDAO.removeOfficer(userConfig, officer);
	}

	public void updateOfficer(UserConfig userConfig, Officer officer)throws PawnException {
		officerDAO.updateOfficer(userConfig, officer);
	}

	public DataBag getAllOfficer(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return officerDAO.getAllOfficer(userConfig, criteriaList);
	}

	public Officer getOfficerById(UserConfig userConfig, int recordId)throws PawnException {
		return officerDAO.getOfficerById(userConfig, recordId);
	}

	public Officer getOfficerByUserName(UserConfig userConfig, String code)throws PawnException {
		return officerDAO.getOfficerByUserName(userConfig, code);
	}
	public Officer getOfficerByPawnerId(UserConfig userConfig,int pawnerId)throws PawnException{
		return officerDAO.getOfficerByPawnerId(userConfig, pawnerId);
	}
}
