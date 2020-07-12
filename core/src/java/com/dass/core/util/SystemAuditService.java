package com.dass.core.util;


import com.dass.core.bean.EventLog;
import com.dass.core.exception.PawnException;
import com.dass.core.util.DataBag;
import com.dass.core.util.UserConfig;


public interface SystemAuditService {
	public void createEventLog(UserConfig userConfig,EventLog eventLog) throws PawnException;
	public DataBag getEventLogById(UserConfig userConfig,int recordId)throws PawnException;
	public DataBag getEventLogByTransactionNo(UserConfig userConfig)throws PawnException;

}
