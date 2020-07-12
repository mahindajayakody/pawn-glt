package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.DayEndDAO;
import com.dass.pawning.domain.Branch;
import com.dass.pawning.service.DayEndService;

public class DayEndServiceImpl implements DayEndService {
	private DayEndDAO dayEndDAO;
	
	public void setDayEndDAO(DayEndDAO dayEndDAO) {
		this.dayEndDAO = dayEndDAO;
	}
	
	public void doDayEndProcess(UserConfig userConfig, Integer[] branchs)throws PawnException {
		dayEndDAO.doDayEndProcess(userConfig, branchs);
	}

	public List<Branch> getInitialData(UserConfig userConfig)throws PawnException {
		return dayEndDAO.getInitialData(userConfig);
	}

}
