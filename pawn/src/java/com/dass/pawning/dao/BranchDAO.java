package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Branch;

public interface BranchDAO {
	void createBranch(UserConfig userConfig,Branch branch);
	void updateBranch(UserConfig userConfig,Branch branch);
	void removeBranch(UserConfig userConfig,Branch branch);
	
	Branch getBranchById(UserConfig userConfig,int recordId);
	Branch getBranchByCode(UserConfig userConfig,String code);
	DataBag getAllBranch(UserConfig userConfig,List<QueryCriteria> criteriaList);
	Branch getBranchByIdWithSystemDate(UserConfig userConfig, int recordId);
}
