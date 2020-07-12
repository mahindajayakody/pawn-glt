package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Branch;

public interface DayEndDAO {
	public void doDayEndProcess(UserConfig userConfig,Integer[] branchs);
	public List<Branch> getInitialData(UserConfig userConfig);
}
