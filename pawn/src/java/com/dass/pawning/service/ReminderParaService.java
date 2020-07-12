package com.dass.pawning.service;

import java.util.List;

import com.dass.core.exception.PawnException;
import com.dass.core.util.AuthorizableService;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.UserConfig;
import com.dass.pawning.domain.ReminderPara;

public interface ReminderParaService extends AuthorizableService {
	public void createReminderPara(UserConfig userConfig,ReminderPara reminderPara) throws PawnException;
	public void updateReminderPara(UserConfig userConfig,ReminderPara reminderPara) throws PawnException;
	public void removeReminderPara(UserConfig userConfig,ReminderPara reminderPara) throws PawnException;
	
	public ReminderPara getReminderParaById(UserConfig userConfig,int recordId)throws PawnException;
	public ReminderPara getReminderParaByCode(UserConfig userConfig,String code)throws PawnException;
	public DataBag getAllReminderPara(UserConfig userConfig,List<QueryCriteria>criteriaList) throws PawnException;
}
