package com.dass.core.util;

import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.UserConfig;
import com.dass.core.bean.EventLog;

public class SystemAuditServiceImpl implements SystemAuditService {
	private SystemAuditDAO systemAuditDAO;
	

	public void setSystemAuditDAO(SystemAuditDAO systemAuditDAO) {
		this.systemAuditDAO = systemAuditDAO;
	}

	public void createEventLog(UserConfig userConfig, EventLog eventLog) throws PawnException{
		systemAuditDAO.createEventLog(userConfig, eventLog);
	}

	public DataBag getEventLogById(UserConfig userConfig, int recordId) throws PawnException{
		return systemAuditDAO.getEventLogById(userConfig, recordId);
	}

	public DataBag getEventLogByTransactionNo(UserConfig userConfig) throws PawnException{
		return systemAuditDAO.getEventLogByTransactionNo(userConfig);
	}
}
