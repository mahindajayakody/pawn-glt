package com.dass.core.util;

import com.dass.core.bean.EventLog;
import com.dass.core.util.DataBag;
import com.dass.core.util.UserConfig;

public interface SystemAuditDAO {
	public void createEventLog(UserConfig userConfig,EventLog eventLog);
	public DataBag getEventLogById(UserConfig userConfig,int recordId);
	public DataBag getEventLogByTransactionNo(UserConfig userConfig);
}
