package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.domain.Location;

public interface BranchService{
	void createBranch(UserConfig userConfig,Branch branch)throws PawnException;
	void updateBranch(UserConfig userConfig,Branch branch)throws PawnException;
	void removeBranch(UserConfig userConfig,Branch branch)throws PawnException;

	Branch getBranchById(UserConfig userConfig,int recordId)throws PawnException;
	Branch getBranchByCode(UserConfig userConfig,String code)throws PawnException;
	DataBag getAllBranch(UserConfig userConfig,List<QueryCriteria> criteriaList)throws PawnException;
	Branch getBranchByIdWithSystemDate(UserConfig userConfig, int recordId)throws PawnException;
}
