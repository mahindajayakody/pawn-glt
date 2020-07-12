package com.dass.pawning.dao;


import java.util.List;

import com.dass.core.util.AuthorizableDAO;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ReminderPara;

public interface ReminderParaDAO extends AuthorizableDAO {
	public void createReminderPara(UserConfig userConfig,ReminderPara reminderPara);
	public void updateReminderPara(UserConfig userConfig,ReminderPara reminderPara);
	public void removeReminderPara(UserConfig userConfig,ReminderPara reminderPara);
	
	public DataBag getAllReminderPara(UserConfig userConfig,List<QueryCriteria> criteriaList);
	public ReminderPara getReminderParaById(UserConfig userConfig,int recordId);
	public ReminderPara getReminderParaByCode(UserConfig userConfig,String code);

}
