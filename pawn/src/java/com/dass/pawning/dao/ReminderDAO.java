package com.dass.pawning.dao;

import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.Reminder;

public interface ReminderDAO extends AuthorizableDAO {

	public DataBag getAllReminder(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public Reminder getReminderById(UserConfig userConfig,int recordId);
	public Reminder getReminderByCode(UserConfig userConfig,String code);
	public void updateReminder(UserConfig userConfig,Reminder reminder);
}
