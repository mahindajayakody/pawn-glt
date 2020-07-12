package com.dass.pawning.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;

import com.dass.core.util.MasterDAOSupport;
import com.dass.core.util.QueryCriteria;
import com.dass.pawning.dao.SystemProgramDAO;
import com.dass.pawning.domain.SystemProgram;


public class SystemProgramDAOImpl extends MasterDAOSupport implements SystemProgramDAO {
	Logger logger = Logger.getLogger(SystemProgramDAOImpl.class);
	
	public List<SystemProgram> getAllSystemPrograms(List<QueryCriteria> criteriaList){
		logger.info("*** Enter in to the getAllSystemPrograms method ***");
		List<SystemProgram> systemProgramList = null;
		Criteria criteria = getSession().createCriteria(SystemProgram.class);
		systemProgramList = getDataList(getHibernateTemplate(), criteriaList, criteria).getDataList();
		logger.info("*** Leaving from the getAllSystemPrograms method ***");
		return systemProgramList;
	}
}
