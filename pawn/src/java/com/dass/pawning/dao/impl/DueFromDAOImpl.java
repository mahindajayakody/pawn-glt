package com.dass.pawning.dao.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dass.core.bean.Trace;
import com.dass.core.exception.CommonDataAccessException;
import com.dass.core.util.DataBag;
import com.dass.core.util.QueryCriteria;
import com.dass.core.util.TransactionDAOSupport;
import com.dass.core.util.UserConfig;
import com.dass.pawning.dao.DueFromDAO;
import com.dass.pawning.domain.DueFrom;
import com.dass.pawning.domain.DueType;

public class DueFromDAOImpl extends TransactionDAOSupport implements DueFromDAO {
	private static final Logger logger = Logger.getLogger(DueFromDAOImpl.class);

	@Override
	public void createDueFrom(UserConfig userConfig, DueFrom dueFrom) {
		logger.debug("**** Enter in to createDueFrom method ****");
		initializeTransactionDomainObject(userConfig, dueFrom);
		getHibernateTemplate().save(dueFrom);
		logger.debug("**** Leaving from createDueFrom method ****");
	}

	@Override
	public void removeDueFrom(UserConfig userConfig, DueFrom dueFrom) {
		logger.debug("**** Enter in to removeDueFrom method ****");
		// remove(userConfig, dueFrom);
		logger.debug("**** Leaving from removeDueFrom method ****");
	}

	@Override
	public void updateDueFrom(UserConfig userConfig, DueFrom dueFrom) {
		logger.debug("**** Enter in to updateDueFrom method ****");
		Session session = getSession();
		session.evict(dueFrom);
		
		DueFrom currentDueFrom = getDueFromById(userConfig, dueFrom.getDueFromId());
		currentDueFrom.setDueAmount(currentDueFrom.getDueAmount() + dueFrom.getDueAmount());
		currentDueFrom.setBalanceAmount(currentDueFrom.getBalanceAmount()+dueFrom.getBalanceAmount());
		// dueFrom.setCompanyId(userConfig.getCompanyId());
		// dueFrom.setProductId(1);
		// update(userConfig, dueFrom);
		getHibernateTemplate().update(currentDueFrom);
		logger.debug("**** Leaving from updateDueFrom method ****");
	}

	@Override
	public DataBag getAllDueFrom(UserConfig userConfig, List<QueryCriteria> criteriaList) {
		logger.debug("**** Enter in to getAllDueFrom method ****");
		DataBag dueFromBag = null;
		Criteria criteria = getSession().createCriteria(DueFrom.class);
		dueFromBag = getDataList(getHibernateTemplate(), criteriaList, criteria);
		logger.debug("**** Leaving from getAlldueFrom method ****");
		return dueFromBag;
	}

	@Override
	public List<DueFrom> getDueFromByFacility(UserConfig userConfig, int ticketId) {
		logger.debug("**** Enter in to getDueFromByCode method ****");
		List<DueFrom> dueFromList = null;
		Criteria criteria = getSession().createCriteria(DueFrom.class);
		//Trello issue Id 34 fixed By Mahinda
		criteria.add(Restrictions.eq("ticketId", ticketId));
		criteria.add(Restrictions.eq("dueTypeId", 2));
		dueFromList = criteria.list();

		logger.debug("**** Leaving from getDueFromByCode method ****");
		return dueFromList;
	}

	@Override
	public DueFrom getDueFromById(UserConfig userConfig, int recordId) {
		logger.debug("**** Enter in to getDueFromById method ****");
		DueFrom dueFrom = null;
		dueFrom = (DueFrom) getHibernateTemplate().get(DueFrom.class, Integer.valueOf(recordId));

		if (dueFrom == null)
			throw new CommonDataAccessException("errors.recordnotfound");

		logger.debug("**** Leaving from getDueFromById method ****");
		return dueFrom;
	}

	@Override
	public DueType getDueTypeByDueCode(UserConfig userConfig, String DueTypeCode) {

		Criteria dueTypeCriteria = getSession().createCriteria(DueType.class);
		dueTypeCriteria.add(Restrictions.eq("dueType", DueTypeCode));
		DueType dueType = (DueType) dueTypeCriteria.uniqueResult();

		return dueType;
	}

	@Override
	public void authorizeCreation(UserConfig userConfig, Trace trace,
			boolean authorize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void authorizeDeletion(UserConfig userConfig, Trace trace,
			boolean authorize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void authorizeUpdation(UserConfig userConfig, Trace trace,
			boolean authorize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DueFrom> getAllDueFrom(UserConfig userConfig, int ticketId) {
		logger.debug("**** Enter in to getAllDueFrom method ****");
		List<DueFrom> dueFromBag = null;
		Criteria criteria = getSession().createCriteria(DueFrom.class);
		criteria.add(Restrictions.eq("ticketId", ticketId));
		dueFromBag =  criteria.list();
		logger.debug("**** Leaving from getAlldueFrom method ****");
		return dueFromBag;
	}

}
