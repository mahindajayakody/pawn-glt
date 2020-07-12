package com.dass.pawning.service.impl;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableServiceImpl;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.ReminderDAO;
import com.dass.pawning.domain.Reminder;
import com.dass.pawning.service.ReminderService;

public class ReminderServiceImpl extends AuthorizableServiceImpl implements ReminderService {

	private ReminderDAO reminderDAO;
	public void setReminderDAO(ReminderDAO reminderDAO) {
		this.reminderDAO = reminderDAO;
	}
	public DataBag getAllReminder(UserConfig userConfig,List<QueryCriteria> criteriaList) throws PawnException {
		return reminderDAO.getAllReminder(userConfig, criteriaList);
	}

	public Reminder getReminderById(UserConfig userConfig, int recordId)throws PawnException {
		return reminderDAO.getReminderById(userConfig, recordId);
	}

	public void updateReminder(UserConfig userConfig, Reminder reminder)throws PawnException {
		reminderDAO.updateReminder(userConfig, reminder);
	}
	public Reminder getReminderByCode(UserConfig userConfig, String code)
			throws PawnException {
		return reminderDAO.getReminderByCode(userConfig, code);
	}
	

}
