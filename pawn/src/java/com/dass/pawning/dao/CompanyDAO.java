package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Company;

public interface CompanyDAO {
	public void createCompany(UserConfig userConfig,Company company);
	public void updateCompany(UserConfig userConfig,Company company);
	public void removeCompany(UserConfig userConfig,Company company);
	
	public Company getCompanyById(UserConfig userConfig,int recordId);
	public Company getCompanyByCode(UserConfig userConfig,String code);
	public DataBag getAllCompany(UserConfig userConfig,List<QueryCriteria> criteriaList);

}
