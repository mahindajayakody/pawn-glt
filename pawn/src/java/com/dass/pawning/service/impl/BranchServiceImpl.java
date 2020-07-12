package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.BranchDAO;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.service.BranchService;

public class BranchServiceImpl implements BranchService {
	private BranchDAO branchDAO;
	
	public void setBranchDAO(BranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public void createBranch(UserConfig userConfig, Branch branch) throws PawnException {
		branchDAO.createBranch(userConfig, branch);
	}

	public void removeBranch(UserConfig userConfig, Branch branch) throws PawnException {
		branchDAO.removeBranch(userConfig, branch);
	}

	public void updateBranch(UserConfig userConfig, Branch branch) throws PawnException {
		branchDAO.updateBranch(userConfig, branch);
	}
	
	public DataBag getAllBranch(UserConfig userConfig, List<QueryCriteria> criteriaList) throws PawnException {
		return branchDAO.getAllBranch(userConfig, criteriaList);
	}

	public Branch getBranchByCode(UserConfig userConfig, String code) throws PawnException {
		return branchDAO.getBranchByCode(userConfig, code);
	}

	public Branch getBranchById(UserConfig userConfig, int recordId) throws PawnException {
		return branchDAO.getBranchById(userConfig, recordId);
	}

	public Branch getBranchByIdWithSystemDate(UserConfig userConfig, int recordId) throws PawnException {
		return branchDAO.getBranchByIdWithSystemDate(userConfig, recordId);
	}
}
