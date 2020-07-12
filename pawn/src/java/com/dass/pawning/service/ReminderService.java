package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Reminder;

public interface ReminderService extends AuthorizableService {
	public DataBag getAllReminder(UserConfig userConfig,List<QueryCriteria>criteriaList ) throws PawnException;
	public Reminder getReminderById(UserConfig userConfig,int recordId) throws PawnException;
	public Reminder getReminderByCode(UserConfig userConfig,String code) throws PawnException;
	public void updateReminder(UserConfig userConfig,Reminder reminder)throws PawnException;
}
