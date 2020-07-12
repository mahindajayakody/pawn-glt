package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Branch;

public interface DayEndService {
	public void doDayEndProcess(UserConfig userConfig,Integer[] branchs)throws PawnException;
	public List<Branch> getInitialData(UserConfig userConfig)throws PawnException;
}
