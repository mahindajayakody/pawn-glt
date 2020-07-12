package com.dass.pawning.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ReminderParaDAO;
import com.dass.pawning.domain.ReminderPara;
import com.dass.pawning.service.ReminderParaService;

public class ReminderParaServiceImpl extends AuthorizableServiceImpl implements	ReminderParaService {
	private ReminderParaDAO reminderParaDAO;
	
	public void setReminderParaDAO(ReminderParaDAO reminderParaDAO) {
		this.reminderParaDAO = reminderParaDAO;
	}

	public void createReminderPara(UserConfig userConfig,ReminderPara reminderPara) throws PawnException {
		reminderParaDAO.createReminderPara(userConfig, reminderPara);
	}

	public DataBag getAllReminderPara(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return reminderParaDAO.getAllReminderPara(userConfig, criteriaList);
	}

	public ReminderPara getReminderParaByCode(UserConfig userConfig, String code)throws PawnException {
		return reminderParaDAO.getReminderParaByCode(userConfig, code);
	}

	public ReminderPara getReminderParaById(UserConfig userConfig, int recordId)throws PawnException {
		return reminderParaDAO.getReminderParaById(userConfig, recordId);
	}

	public void removeReminderPara(UserConfig userConfig,ReminderPara reminderPara) throws PawnException {
		reminderParaDAO.removeReminderPara(userConfig, reminderPara);
	}

	public void updateReminderPara(UserConfig userConfig,ReminderPara reminderPara) throws PawnException {
		reminderParaDAO.updateReminderPara(userConfig, reminderPara);
	}
}
