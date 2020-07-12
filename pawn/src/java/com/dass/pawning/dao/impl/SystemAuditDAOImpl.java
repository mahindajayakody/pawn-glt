package com.dass.pawning.dao.impl;

import org.apache.log4j.Logger;

import com.dass.core.util.BaseDAOSupport;
import com.dass.core.util.DataBag;
import com.dass.core.util.UserConfig;
import com.dass.core.util.SystemAuditDAO;
import com.dass.core.bean.EventLog;

public class SystemAuditDAOImpl extends BaseDAOSupport implements SystemAuditDAO {

	private static final Logger logger = Logger.getLogger(SystemAuditDAOImpl.class); 
	public void createEventLog(UserConfig userConfig, EventLog eventLog) {
		logger.debug("**** Enter in to createEventLog ****");
		initializeDomainObject(userConfig, eventLog);
		getHibernateTemplate().save(eventLog);
		logger.debug("**** Enter in to createEventLog ****");
	}

	public DataBag getEventLogById(UserConfig userConfig, int recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataBag getEventLogByTransactionNo(UserConfig userConfig) {
		// TODO Auto-generated method stub
		return null;
	}


}
